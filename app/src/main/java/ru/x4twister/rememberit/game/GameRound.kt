/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.game

import ru.x4twister.rememberit.model.Topic
import kotlin.math.max

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

    fun checkAnswer(answer: String)=
        if (answer==question.answer) {
            question.mistake=max(-1, question.mistake - 1)
            true
        } else {
            question.mistake=question.mistake+2
            false
        }

    fun isTopicCompleted()=topic.questions.none { it.mistake > -1 }
}