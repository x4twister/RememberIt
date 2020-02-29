/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit

import androidx.databinding.BaseObservable

class TopicViewModel: BaseObservable() {

    val title
        get() = topic!!.name

    var topic:Topic? = null
        set(value) {
            field = value
            notifyChange()
        }
}