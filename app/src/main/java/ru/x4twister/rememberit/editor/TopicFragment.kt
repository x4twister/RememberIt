/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.editor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.x4twister.rememberit.R
import ru.x4twister.rememberit.Topic
import ru.x4twister.rememberit.TopicLab
import ru.x4twister.rememberit.databinding.FragmentTopicBinding
import ru.x4twister.rememberit.databinding.ListItemQuestionBinding
import java.util.*

class TopicFragment: Fragment() {

    interface Callback{
        fun onTopicDeleted()
    }

    val callback: Callback by lazy {
        activity as Callback
    }

    private var topicId:UUID?=null

    private val topic:Topic by lazy {
        TopicLab.getTopic(topicId!!)!!
    }

    private val topicViewModel by lazy {
        EditorViewModel(topic,object: EditorViewModel.Callback{
            override fun onQuestionDeleted() {
                updateUI()
            }
        })
    }

    private val questionAdapter by lazy {
        QuestionAdapter(topic.questions)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        topicId=arguments!!.getSerializable(ARG_TOPIC_ID) as UUID

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentTopicBinding =DataBindingUtil
            .inflate(inflater, R.layout.fragment_topic,container,false)

        binding.viewModel=topicViewModel

        binding.recycleView.run {
            layoutManager= LinearLayoutManager(activity)
            adapter=questionAdapter
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.fragment_topic,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.title){
            "Edit" -> {
                topicViewModel.editMode=topicViewModel.editMode.not()
                true
            }
            "Rename topic" -> {
                val dialog=EditTextFragment.newInstance(topic.name,"name")
                dialog.setTargetFragment(this,REQUEST_TEXT)
                dialog.show(fragmentManager!!,DIALOG_TEXT)
                true
            }
            "Delete topic" -> {
                TopicLab.deleteTopic(topic)
                callback.onTopicDeleted()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode!=Activity.RESULT_OK)
            return

        if (requestCode==REQUEST_TEXT){
            topic.name=data!!.getStringExtra(EditTextFragment.EXTRA_TEXT)!!
            topicViewModel.notifyChange()
        }
    }

    private fun updateUI() {
        questionAdapter.run {
            setQuestions(topic.questions)
            notifyDataSetChanged()
        }
    }

    companion object{

        const val ARG_TOPIC_ID="topic_id"
        const val DIALOG_TEXT="DialogText"
        const val REQUEST_TEXT=0

        fun newInstance(topicId: UUID): TopicFragment {
            val args=Bundle()
            args.putSerializable(ARG_TOPIC_ID,topicId)

            val fragment=TopicFragment()
            fragment.arguments=args
            return fragment
        }
    }

    inner class QuestionHolder(private val binding: ListItemQuestionBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(question: Topic.Question) {
            binding.viewModel!!.question=question
        }

        init {
            binding.viewModel=QuestionViewModel(object :QuestionViewModel.Callback{
                override fun questionClicked(question: Topic.Question) {
                    topicViewModel.currentQuestion=question
                }
            })
        }
    }

    inner class QuestionAdapter(private var questions: List<Topic.Question>): RecyclerView.Adapter<QuestionHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder {
            val inflater=LayoutInflater.from(activity)
            val binding:ListItemQuestionBinding=DataBindingUtil
                .inflate(inflater,R.layout.list_item_question,parent,false)

            return QuestionHolder(binding)
        }

        override fun getItemCount()=questions.size

        override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
            holder.bind(questions[position])
        }

        fun setQuestions(newQuestions: List<Topic.Question>){
            questions=newQuestions
        }
    }
}