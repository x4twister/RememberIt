/*
 * Copyright (c) 2020 x4twister
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */

package ru.x4twister.rememberit.game

import android.speech.tts.TextToSpeech
import androidx.databinding.BaseObservable

class GameViewModel(private val round: GameRound,private val tts: TextToSpeech): BaseObservable() {

    val subject
        get() = round.title()

    fun speak()=tts.speak(round.fullTitle(), TextToSpeech.QUEUE_FLUSH, null, "")
}