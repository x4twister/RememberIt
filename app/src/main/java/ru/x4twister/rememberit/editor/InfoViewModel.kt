/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.editor

import androidx.databinding.BaseObservable
import ru.x4twister.rememberit.model.Topic
import kotlin.math.max

class InfoViewModel(val topic: Topic): BaseObservable() {

    val totalQuestions="Total: ${topic.questions.size}"

    val passedQuestions="Passed: ${topic.questions.filter { it.mistake==-1 }.size.let { 
        "$it (${(it*100)/max(topic.questions.size,1)}%)"
    }}"

    val mistakenQuestions="Mistaken: ${topic.questions.filter { it.mistake>0 }.size}"

    val lastMistakeQuestion="Last mistake: ${topic.questions
        .sortedBy { it.mistake }
        .lastOrNull { it.mistake > 0 }?.let{"${it.subject} (${it.answer})"}?:"None"}"
}