/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit

import android.content.Context
import ru.x4twister.rememberit.editor.TopicActivity

/**
 * data binding is only for layout resources -> temporary solution
 */
object TopicMenuViewModel {

    fun addTopic(context: Context, onTopicAdded: () -> Unit){
        val topic=Topic("New topic", listOf(Topic.Question("Subject","Answer")))
        TopicLab.addTopic(topic)

        val intent=TopicActivity.newIntent(context,topic.id)
        context.startActivity(intent)

        onTopicAdded()
    }
}