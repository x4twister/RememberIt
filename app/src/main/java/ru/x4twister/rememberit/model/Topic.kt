/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.model

import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.kotlin.createObject
import java.util.*

open class Topic : RealmObject() {

    @PrimaryKey
    var id: String=""

    var name:String="New topic"
        set(value) {
            realm.beginTransaction()
            field = value
            realm.commitTransaction()
        }

    var questions:RealmList<Question> = RealmList()

    fun createQuestion(): Question {
        realm.beginTransaction()
        val question= Question.newInstance()
        questions.add(question)
        realm.commitTransaction()

        return question
    }

    fun deleteQuestion(question: Question){
        realm.beginTransaction()
        questions.remove(question)
        question.deleteFromRealm()
        realm.commitTransaction()
    }

    companion object {
        fun newInstance()=Realm.getDefaultInstance().createObject<Topic>(UUID.randomUUID().toString())
    }
}