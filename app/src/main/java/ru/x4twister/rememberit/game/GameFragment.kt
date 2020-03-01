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

class GameFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentGameBinding=DataBindingUtil
            .inflate(inflater,R.layout.fragment_game,container,false)

        return binding.root
    }

    companion object {
        fun newInstance() = GameFragment()
    }
}