package com.fabinpaul.play2048.logic

interface `2048` {

    fun swipeRight()

    fun swipeLeft()

    fun swipeUp()

    fun swipeDown()

    fun getCurrentScore(): Int

    fun newGame()
}