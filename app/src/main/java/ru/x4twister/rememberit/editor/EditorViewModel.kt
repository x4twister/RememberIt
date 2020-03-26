/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.editor

import android.view.View
import androidx.databinding.BaseObservable
import ru.x4twister.rememberit.model.Question
import ru.x4twister.rememberit.model.Topic
import ru.x4twister.rememberit.game.GameActivity

class EditorViewModel(val topic: Topic, val callback:Callback): BaseObservable() {

    interface Callback{
        fun onQuestionAdded(currentQuestion: Question)
        fun onQuestionEdited(currentQuestion: Question)
        fun onQuestionDeleted()
    }

    var editMode: Boolean=false
        set(value) {
            field = value
            notifyChange()
        }

    var currentQuestion: Question? = nextQuestion()
        set(value) {
            field = value
            notifyChange()
        }

    private fun nextQuestion()=
        topic.questions.firstOrNull()

    fun addQuestion(){
        currentQuestion=topic.createQuestion()
        callback.onQuestionAdded(currentQuestion!!)
    }

    fun editQuestion(){
        callback.onQuestionEdited(currentQuestion!!)
    }

    fun deleteQuestion(){
        topic.deleteQuestion(currentQuestion!!)
        currentQuestion=nextQuestion()
        callback.onQuestionDeleted()
    }

    fun play(view: View){
        val context=view.context
        val intent= GameActivity.newIntent(context,topic.id)
        context.startActivity(intent)
    }

    fun editVisibility()=
        if (editMode)
            View.VISIBLE
        else
            View.GONE
}