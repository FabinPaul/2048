package com.fabinpaul.play2048.logic

import com.fabinpaul.play2048.ui.game.GameViewModel
import java.util.*
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class Game2048Impl @Inject constructor(
    @Named("gridSize")
    private val gridSize: Int
) : Game2048 {

    private var currentScore = 0
    private val gameGrid = GameGrid(size = gridSize)
    private val random = Random()

    override fun swipeRight() {

    }

    override fun swipeLeft() {

    }

    override fun swipeUp() {
        for (i in 0 until gridSize) {
            for (j in 0 until gridSize) {
                for (k in i + 1 until gridSize) {
                    val currentTile = gameGrid.tiles[i][j]
                    val nextTile = gameGrid.tiles[k][j]
                    if (currentTile?.value?.value == null && nextTile?.value?.value != null) {
                        currentTile?.setValue(nextTile.value.value)
                        nextTile.setValue(null)
                    } else if (currentTile?.value?.value != null && nextTile?.value?.value == currentTile.value.value) {
                        val newValue = currentTile.value.value?.plus(nextTile?.value?.value ?: 0)
                        currentTile.setValue(newValue)
                        if (newValue != null) {
                            currentScore += newValue
                        }
                        nextTile?.setValue(null)
                    }
                }
            }
        }
        addNewField()
    }

    override fun swipeDown() {

    }

    override fun getCurrentScore(): Int {
        return currentScore
    }

    override fun newGame(): GameGrid {
        for (i in 0 until GameViewModel.GRID_SIZE) {
            for (j in 0 until GameViewModel.GRID_SIZE) {
                gameGrid.tiles[i][j] = Tile(i, j)
            }
        }
        currentScore = 0
        addNewField()
        addNewField()
        return gameGrid
    }

    private fun addNewField() {
        var tile: Tile?
        var x: Int
        var y: Int

        do {
            x = random.nextInt(gridSize)
            y = random.nextInt(gridSize)
            tile = gameGrid.tiles[x][y]
        } while (tile != null && tile.value.value != null)

        gameGrid.tiles[x][y]?.setValue(2 * (1 + random.nextInt(2)))
    }
}