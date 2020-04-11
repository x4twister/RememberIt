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
            realm.executeTransaction {
                field = value
            }
        }

    var answer:String="Answer"
        set(value) {
            realm.executeTransaction {
                field = value
            }
        }

    var mistake: Int=0
        set(value) {
            realm.executeTransaction {
                field = value
            }
        }

    fun reset(){
        mistake=0
    }

    companion object {
        fun newInstance()=Realm.getDefaultInstance().createObject<Question>()
    }
}