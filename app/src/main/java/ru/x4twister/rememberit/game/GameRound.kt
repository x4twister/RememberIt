/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.game

import ru.x4twister.rememberit.Topic

class GameRound(private val topic: Topic) {

    private val question=topic.questions.random()

    val answers=topic.questions.map {
        it.answer
    }.shuffled()

    fun title()="${question.subject} (${question.mistake})"

    fun checkAnswer(answer: String)=
        if (answer==question.answer) {
            question.mistake=Integer.max(0, question.mistake - 1)
            true
        } else {
            question.mistake=question.mistake+1
            false
        }
}