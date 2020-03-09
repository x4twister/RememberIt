/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.editor

import android.view.View
import ru.x4twister.rememberit.Topic
import ru.x4twister.rememberit.game.GameActivity

class EditorViewModel(val topic: Topic) {

    val currentQuestion: Topic.Question = topic.questions.first()

    fun play(view: View){
        val context=view.context
        val intent= GameActivity.newIntent(context,topic.id)
        context.startActivity(intent)
    }
}