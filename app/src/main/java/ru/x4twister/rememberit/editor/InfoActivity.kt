/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.editor

import android.content.Context
import android.content.Intent
import ru.x4twister.rememberit.SingleFragmentActivity

class InfoActivity: SingleFragmentActivity() {

    override fun createFragment(): InfoFragment {
        val topicId=intent.getSerializableExtra(EXTRA_TOPIC_ID) as String
        return InfoFragment.newInstance(topicId)
    }

    companion object {

        const val EXTRA_TOPIC_ID="ru.x4twister.rememberit.editor.topic_id"

        fun newIntent(packageContext: Context, topicId: String): Intent {
            val intent= Intent(packageContext,InfoActivity::class.java)
            intent.putExtra(EXTRA_TOPIC_ID,topicId)
            return intent
        }
    }
}