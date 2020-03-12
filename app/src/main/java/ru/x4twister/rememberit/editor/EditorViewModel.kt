/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.editor

import android.view.View
import androidx.databinding.BaseObservable
import ru.x4twister.rememberit.Topic
import ru.x4twister.rememberit.game.GameActivity

class EditorViewModel(val topic: Topic): BaseObservable() {

    var editMode: Boolean=false
        set(value) {
            field = value
            notifyChange()
        }

    // TODO а если первого не будет?
    var currentQuestion: Topic.Question = topic.questions.first()
        set(value) {
            field = value
            notifyChange()
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