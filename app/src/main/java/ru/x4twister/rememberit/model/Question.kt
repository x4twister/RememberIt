/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.model

import io.realm.Realm
import io.realm.RealmObject
import io.realm.kotlin.createObject


open class Question : RealmObject() {

    var subject:String="Subject"
        set(value) {
            realm.beginTransaction()
            field = value
            realm.commitTransaction()
        }

    var answer:String="Answer"
        set(value) {
            realm.beginTransaction()
            field = value
            realm.commitTransaction()
        }

    var mistake: Int=0
        set(value) {
            realm.beginTransaction()
            field = value
            realm.commitTransaction()
        }

    companion object {
        fun newInstance()=Realm.getDefaultInstance().createObject<Question>()
    }
}