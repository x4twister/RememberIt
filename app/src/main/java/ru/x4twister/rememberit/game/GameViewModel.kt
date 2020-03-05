/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.game

import androidx.databinding.BaseObservable

class GameViewModel(private val round: GameRound): BaseObservable() {
    val subject
        get() = "${round.question.subject} (${round.question.mistake})"
}