/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class SingleFragmentActivity: AppCompatActivity() {

    protected abstract fun createFragment(): Fragment

    @get:LayoutRes
    protected val layoutResId=R.layout.activity_fragment

    protected val containerId=R.id.fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)

        val fm = supportFragmentManager
        var fragment = fm.findFragmentById(containerId)
        if (fragment == null) {
            fragment = createFragment()
            fm.beginTransaction()
                .add(containerId, fragment)
                .commit()
        }
    }
}