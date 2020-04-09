/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.model

import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where

object TopicLab{

    private val realm = Realm.getDefaultInstance()

    val topics: RealmResults<Topic>
        get() {
            return realm.where<Topic>().findAll()
        }

    fun getTopic(id: String)=
        realm.where<Topic>().equalTo("id",id).findFirst()

    fun createTopic(): Topic {
        realm.beginTransaction()
        val topic= Topic.newInstance()
        realm.commitTransaction()

        return topic
    }

    fun deleteTopic(topic: Topic) {
        realm.executeTransaction {
            topic.deleteFromRealm()
        }
    }

    fun createTopicFromText(name: String, data: String): Topic {

        val topic= createTopic()

        if (name.isNotEmpty())
            topic.name=name.removeSuffix(".txt")

        if (data.isNotEmpty()){
            data.split("\n").forEach { line ->
                line.split(",,").let {(subject,answer)->
                    val question=topic.createQuestion()
                    question.subject=subject
                    question.answer=answer
                }
            }
        }

        return topic
    }
}