/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit

import android.app.Application
import io.realm.Realm

class RememberApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
    }
}