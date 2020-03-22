/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.editor

import android.content.Context
import android.content.Intent
import ru.x4twister.rememberit.SingleFragmentActivity

class TopicActivity: SingleFragmentActivity(), TopicFragment.Callback {

    override fun createFragment(): TopicFragment {
        val topicId=intent.getSerializableExtra(EXTRA_TOPIC_ID) as String
        return TopicFragment.newInstance(topicId)
    }

    companion object {

        const val EXTRA_TOPIC_ID="ru.x4twister.rememberit.editor.topic_id"

        fun newIntent(packageContext: Context, topicId: String): Intent {
            val intent= Intent(packageContext,TopicActivity::class.java)
            intent.putExtra(EXTRA_TOPIC_ID,topicId)
            return intent
        }
    }

    override fun onTopicDeleted() {
        finish()
    }
}