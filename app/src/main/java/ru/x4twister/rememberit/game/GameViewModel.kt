/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.game

import androidx.databinding.BaseObservable
import ru.x4twister.rememberit.TopicLab
import java.util.*

class GameViewModel(topicId: UUID): BaseObservable() {

    private val topic=TopicLab.getTopic(topicId)!!
    val question=topic.questions.random()
    val answers=topic.questions.map {
        it.answer
    }.shuffled()
}