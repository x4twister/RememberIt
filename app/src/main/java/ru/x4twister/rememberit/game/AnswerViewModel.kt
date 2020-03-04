/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.game

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BaseObservable
import ru.x4twister.rememberit.Topic
import java.lang.Integer.max

class AnswerViewModel(private val callback:Callback): BaseObservable() {

    interface Callback{
        fun questionChanged()
    }

    var answer:String?=null
        set(value) {
            field = value
            notifyChange()
        }

    var question: Topic.Question?=null

    fun onClick(view: View){
        (view as TextView).let {
            if (it.text==question!!.answer){
                it.setTextColor(Color.GREEN)
                question!!.mistake=max(0,question!!.mistake-1)
            }
            else {
                it.setTextColor(Color.RED)
                question!!.mistake=question!!.mistake+1
            }
        }

        callback.questionChanged()
    }
}