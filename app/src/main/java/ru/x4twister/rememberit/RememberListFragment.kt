/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.x4twister.rememberit.databinding.FragmentRememberListBinding
import ru.x4twister.rememberit.databinding.ListItemTopicBinding
import ru.x4twister.rememberit.model.Topic
import ru.x4twister.rememberit.model.TopicLab
import java.io.InputStreamReader

class RememberListFragment: Fragment() {

    private val topicAdapter by lazy {
        TopicAdapter(TopicLab.topics)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentRememberListBinding=DataBindingUtil
            .inflate(inflater,R.layout.fragment_remember_list,container,false)

        binding.recycleView.run {
            layoutManager=LinearLayoutManager(activity)
            adapter=topicAdapter
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        updateUI()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.fragment_remember_list,menu)
    }

    /** @see #TopicMenuViewModel description */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // R.id.new_topic not found
        return when (item.title) {
            "Create" -> {
                TopicMenuViewModel.addTopic(context!!) { updateUI() }
                true
            }
            "Load" -> {
                val intent=Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory(Intent.CATEGORY_OPENABLE)
                intent.type="text/plain"

                startActivityForResult(intent, REQUEST_DATA)
                true
            }
            else -> onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (resultCode!= Activity.RESULT_OK)
            return

        when (requestCode) {
            REQUEST_DATA -> {
                intent?.data?.also {
                    val name=readNameFromUri(it)
                    val text=readTextFromUri(it)
                    TopicMenuViewModel.addTopic(context!!,name,text) { updateUI() }
                }
            }
        }
    }

    private fun readNameFromUri(uri: Uri): String {
        context!!.contentResolver.query(uri, null, null, null, null)?.use {
            val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            it.moveToFirst()
            return it.getString(nameIndex)
        }

        return ""
    }

    private fun readTextFromUri(uri: Uri): String {
        val inputStream = context!!.contentResolver.openInputStream(uri)!!
        return InputStreamReader(inputStream).buffered().readText()
    }

    private fun updateUI() {
        topicAdapter.run {
            setTopics(TopicLab.topics)
            notifyDataSetChanged()
        }
    }

    companion object {
        const val REQUEST_DATA=0

        fun newInstance()=RememberListFragment()
    }

    inner class TopicHolder(private val binding: ListItemTopicBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(topic: Topic) {
            binding.viewModel!!.topic=topic
        }

        init {
            binding.viewModel=TopicViewModel()
        }
    }

    inner class TopicAdapter(private var topics: List<Topic>): RecyclerView.Adapter<TopicHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicHolder {
            val inflater=LayoutInflater.from(activity)
            val binding:ListItemTopicBinding=DataBindingUtil
                .inflate(inflater,R.layout.list_item_topic,parent,false)

            return TopicHolder(binding)
        }

        override fun getItemCount()=topics.size

        override fun onBindViewHolder(holder: TopicHolder, position: Int) {
            holder.bind(topics[position])
        }

        fun setTopics(newTopics: List<Topic>){
            topics=newTopics
        }
    }
}