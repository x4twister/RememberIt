/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.x4twister.rememberit.databinding.FragmentRememberListBinding
import ru.x4twister.rememberit.databinding.ListItemTopicBinding

class RememberListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentRememberListBinding=DataBindingUtil
            .inflate(inflater,R.layout.fragment_remember_list,container,false)

        binding.recycleView.run {
            layoutManager=LinearLayoutManager(activity)
            adapter=TopicAdapter(listOf(Topic("abc"),Topic("xyz")))
        }

        return binding.root
    }

    companion object {
        fun newInstance()=RememberListFragment()
    }

    inner class TopicHolder(val binding: ListItemTopicBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(topic: Topic) {
            binding.viewModel!!.topic=topic
        }

        init {
            binding.viewModel=TopicViewModel()
        }
    }

    inner class TopicAdapter(private val topics: List<Topic>): RecyclerView.Adapter<TopicHolder>() {

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
    }
}