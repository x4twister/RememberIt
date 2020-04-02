/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.game

import ru.x4twister.rememberit.model.Topic

class GameRound(topic: Topic) {

    private val question=topic.questions.toMutableList()
        .run {
            sortByDescending {
                it.mistake
            }
            takeWhile {
                it.mistake>0
            }.toMutableList()
                .also {
                    (0..Integer.max(1,count()/10)).forEach {_->
                        it.add(random())
                    }
                }
                .distinct()
                .random()
        }

    val answers= (listOf(question.answer)+topic.questions
        .map {
            it.answer
        }.filter {
            it!=question.answer
        }.shuffled().take(4)
            ).shuffled()

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