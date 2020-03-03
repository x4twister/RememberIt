/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit

import java.util.*

object TopicLab{

    val topics:List<Topic> = (1..50).map {
        Topic(UUID.randomUUID(), "Name $it", (1..5).map {
            Topic.Question("Subject $it", "Answer $it")
        })
    }

    fun getTopic(id: UUID)=
        topics.find {
            it.id==id
        }
}