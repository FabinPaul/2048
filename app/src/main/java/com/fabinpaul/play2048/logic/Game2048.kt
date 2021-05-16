package com.fabinpaul.play2048.logic

import androidx.lifecycle.LiveData

interface Game2048 {

    fun swipeRight()

    fun swipeLeft()

    fun swipeUp()

    fun swipeDown()

    fun getCurrentScore(): Int

    fun newGame(): GameGrid

    fun hasWon(): LiveData<Boolean>

    fun hasLost(): LiveData<Boolean>

    fun keepGoing()
}