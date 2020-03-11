/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit

import java.util.*

class Topic(val name:String,val questions:List<Question>){

    val id: UUID=UUID.randomUUID()

    class Question(val subject:String,val answer:String,var mistake: Int=0)
}