/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit

class RememberListActivity : SingleFragmentActivity() {

    override fun createFragment()=RememberListFragment.newInstance()
}
