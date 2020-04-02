/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit

import android.content.Context
import ru.x4twister.rememberit.editor.TopicActivity
import ru.x4twister.rememberit.model.TopicLab

/**
 * data binding is only for layout resources -> temporary solution
 */
object TopicMenuViewModel {

    fun addTopic(context: Context, data: String="", onTopicAdded: () -> Unit){

        val topic= TopicLab.createTopic()

        if (data.isNotEmpty()){
            data.split("\n").forEach { line ->
                line.split(",,").let {
                    val question=topic.createQuestion()
                    question.subject=it[0]
                    question.answer=it[1]
                }
            }
        }

        val intent=TopicActivity.newIntent(context,topic.id)
        context.startActivity(intent)

        onTopicAdded()
    }
}