/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit

import java.util.*

object TopicLab{

    val topics= (1..3).map {
        Topic("Name $it", (1..3).map {
            Topic.Question("Subject $it", "Answer $it")
        })
    } as MutableList<Topic>

    fun getTopic(id: UUID)=
        topics.find {
            it.id==id
        }

    fun addTopic(topic: Topic){
        topics.add(topic)
    }

    fun deleteTopic(topic: Topic) {
        topics.remove(topic)
    }
}