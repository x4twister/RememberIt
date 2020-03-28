/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.game

import ru.x4twister.rememberit.model.Topic

class GameRound(topic: Topic) {

    private val question=topic.questions.toMutableList()
        .run {
            sortBy {
                it.mistake
            }
            takeLast(Integer.max(1,count()/3)).toMutableList()
                .also {
                    it.addAll(take(Integer.max(1,count()/6)))
                    it.add(random())
                }
                .distinct()
                .random()
        }

    val answers= listOf(question.answer)+topic.questions.map {
        it.answer
    }.filter {
        it!=question.answer
    }.shuffled().take(4)

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