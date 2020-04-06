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
import ru.x4twister.rememberit.databinding.FragmentInfoBinding
import ru.x4twister.rememberit.model.TopicLab

class InfoFragment: Fragment() {

    private var topicId:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        topicId=arguments!!.getSerializable(ARG_TOPIC_ID) as String
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentInfoBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_info,container,false)

        binding.viewModel=InfoViewModel(TopicLab.getTopic(topicId)!!)

        return binding.root
    }

    companion object{

        const val ARG_TOPIC_ID="topic_id"

        fun newInstance(topicId: String): InfoFragment {
            val args= Bundle()
            args.putSerializable(ARG_TOPIC_ID,topicId)

            val fragment=InfoFragment()
            fragment.arguments=args
            return fragment
        }
    }
}