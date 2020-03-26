/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit

import android.view.View
import androidx.databinding.BaseObservable
import ru.x4twister.rememberit.editor.TopicActivity
import ru.x4twister.rememberit.model.Topic

class TopicViewModel: BaseObservable() {

    val title
        get() = topic!!.name

    var topic: Topic? = null
        set(value) {
            field = value
            notifyChange()
        }

    fun onClick(view:View){
        val context=view.context
        val intent= TopicActivity.newIntent(context,topic!!.id)
        context.startActivity(intent)
    }
}