/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.game

import android.content.Context
import android.content.Intent
import ru.x4twister.rememberit.SingleFragmentActivity
import java.util.*

class GameActivity: SingleFragmentActivity() {

    override fun createFragment(): GameFragment {
        val topicId=intent.getSerializableExtra(EXTRA_TOPIC_ID) as UUID
        return GameFragment.newInstance(topicId)
    }

    companion object {

        const val EXTRA_TOPIC_ID="ru.x4twister.rememberit.game.topic_id"

        fun newIntent(packageContext: Context,topicId:UUID): Intent {
            val intent=Intent(packageContext,GameActivity::class.java)
            intent.putExtra(EXTRA_TOPIC_ID,topicId)
            return intent
        }
    }
}