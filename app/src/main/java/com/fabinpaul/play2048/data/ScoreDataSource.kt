package com.fabinpaul.play2048.data

interface ScoreDataSource {

    fun getHighScore(): Int

    fun saveHighScore(newHighScore: Int)
}