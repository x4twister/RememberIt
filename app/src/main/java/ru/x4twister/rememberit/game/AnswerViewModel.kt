/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.game

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BaseObservable

class AnswerViewModel(private val gameRound: GameRound,private val callback:Callback): BaseObservable() {

    interface Callback{
        fun answerChecked(correct: Boolean)
    }

    var answer:String?=null
        set(value) {
            field = value
            notifyChange()
        }

    fun onClick(view: View){
        (view as TextView).let {
            val correct=gameRound.checkAnswer(it.text as String)
            it.setTextColor(if (correct) Color.GREEN else Color.RED)

            callback.answerChecked(correct)
        }
    }
}