/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.game

import android.content.Context
import android.content.Intent
import ru.x4twister.rememberit.SingleFragmentActivity

class GameActivity: SingleFragmentActivity() {

    override fun createFragment()=GameFragment.newInstance()

    companion object {
        fun newIntent(packageContext: Context)=Intent(packageContext,GameActivity::class.java)
    }
}