/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.x4twister.rememberit.R
import ru.x4twister.rememberit.TopicLab
import ru.x4twister.rememberit.databinding.FragmentTopicBinding
import java.util.*

class TopicFragment: Fragment() {

    private var topicId:UUID?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        topicId=arguments!!.getSerializable(ARG_TOPIC_ID) as UUID
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentTopicBinding =DataBindingUtil
            .inflate(inflater, R.layout.fragment_topic,container,false)

        binding.viewModel= EditorViewModel(TopicLab.getTopic(topicId!!)!!)

        return binding.root
    }

    companion object{

        const val ARG_TOPIC_ID="topic_id"

        fun newInstance(topicId: UUID): TopicFragment {
            val args=Bundle()
            args.putSerializable(ARG_TOPIC_ID,topicId)

            val fragment=TopicFragment()
            fragment.arguments=args
            return fragment
        }
    }
}