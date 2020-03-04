/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.game

import ru.x4twister.rememberit.Topic

class GameRound(val topic: Topic) {

    val question=topic.questions.random()
    val answers=topic.questions.map {
        it.answer
    }.shuffled()
}