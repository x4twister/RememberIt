/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.game

import android.content.Context
import android.content.Intent
import ru.x4twister.rememberit.SingleFragmentActivity

class GameActivity: SingleFragmentActivity(), GameFragment.Callback {

    override fun createFragment(): GameFragment {
        val topicId=intent.getSerializableExtra(EXTRA_TOPIC_ID) as String
        return GameFragment.newInstance(topicId)
    }

    companion object {

        const val EXTRA_TOPIC_ID="ru.x4twister.rememberit.game.topic_id"

        fun newIntent(packageContext: Context,topicId:String): Intent {
            val intent=Intent(packageContext,GameActivity::class.java)
            intent.putExtra(EXTRA_TOPIC_ID,topicId)
            return intent
        }
    }

    override fun newRound() {
        // each round is a new fragment
        replaceFragment(createFragment())
    }

    private fun replaceFragment(fragment: GameFragment){
        supportFragmentManager.beginTransaction()
            .replace(containerId,fragment)
            .commit()
    }
}