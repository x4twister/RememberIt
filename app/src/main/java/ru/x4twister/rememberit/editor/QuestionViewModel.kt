/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.editor

import androidx.databinding.BaseObservable
import ru.x4twister.rememberit.model.Question

/** ViewModel for recyclerView item in TopicFragment */
class QuestionViewModel(private val callback:Callback): BaseObservable() {

    interface Callback{
        fun questionClicked(question: Question)
    }

    var question: Question?=null
        set(value) {
            field = value
            notifyChange()
        }

    fun onClick(){
        callback.questionClicked(question!!)
    }
}