package com.fabinpaul.play2048.logic

import androidx.lifecycle.MutableLiveData

data class Tile(
    val x: Int = 0,
    val y: Int = 0,
    val value: MutableLiveData<Int> = MutableLiveData<Int>(),
    val bgColor: MutableLiveData<Int> = MutableLiveData<Int>()
)