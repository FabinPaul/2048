package com.fabinpaul.play2048.logic

class GameGrid(
    val size: Int = 4,
    val tiles: Array<Array<Tile?>> = Array(size) { arrayOfNulls(size) }
)
