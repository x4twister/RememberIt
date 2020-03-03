/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import ru.x4twister.rememberit.R
import ru.x4twister.rememberit.databinding.FragmentGameBinding
import java.util.*

class GameFragment: Fragment() {

    var topicId:UUID?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        topicId=arguments!!.getSerializable(ARG_TOPIC_ID) as UUID
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentGameBinding=DataBindingUtil
            .inflate(inflater,R.layout.fragment_game,container,false)

        binding.viewModel=GameViewModel(topicId!!)

        return binding.root
    }

    companion object {

        const val ARG_TOPIC_ID="topic_id"

        fun newInstance(topicId:UUID): GameFragment {
            val args=Bundle()
            args.putSerializable(ARG_TOPIC_ID,topicId)

            val fragment=GameFragment()
            fragment.arguments=args
            return fragment
        }
    }
}