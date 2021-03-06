/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.game

import ru.x4twister.rememberit.model.Topic
import kotlin.math.max
import kotlin.math.min

class GameRound(val topic: Topic) {

    private val question=topic.questions.toMutableList()
        .run {
            sortByDescending {
                it.mistake
            }
            takeWhile {
                it.mistake>0
            }.toMutableList()
                .also { result ->

                    if (result.size<10){
                        removeAll(result)
                        result.addAll(takeWhile {
                            it.mistake==0
                        }.shuffled().take(10))

                        if (result.size<10)
                            result.addAll(shuffled().take(10-result.size))
                    }
                }
                .random()
        }

    val answers= (listOf(question.answer)+topic.questions
        .map {
            it.answer
        }.filter {
            it!=question.answer
        }.shuffled().take(4)
            ).shuffled()

    fun title()= question.subject

    fun fullTitle()=title().run {
        "*".repeat(question.answer.length).let {
            if (!contains(it))
                this
            else
                replace(it,question.answer)
        }
    }

    fun checkAnswer(answer: String)=
        if (answer==question.answer) {
            question.mistake=max(-1, question.mistake - 1)
            true
        } else {
            question.mistake=min(10,question.mistake+2)
            false
        }

    fun isTopicCompleted()=topic.questions.none { it.mistake > -1 }
}