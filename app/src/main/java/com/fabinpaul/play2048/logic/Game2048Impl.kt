package com.fabinpaul.play2048.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _hasLost = MutableLiveData(false)
    val hasLost: LiveData<Boolean>
        get() = _hasLost

    private val _hasWon = MutableLiveData(false)
    val hasWon: LiveData<Boolean>
        get() = _hasWon

    override fun swipeRight() {
        if (_hasLost.value == true) {
            return
        }
        var modified = false
        for (i in 0 until gridSize) {
            for (j in gridSize - 1 downTo 0) {
                for (k in j - 1 downTo 0) {
                    val currentTile = gameGrid.tiles[i][j]
                    val nextTile = gameGrid.tiles[i][k]
                    if (currentTile?.nextPossibleValue == null && nextTile?.nextPossibleValue != null) {
                        currentTile?.nextPossibleValue = (nextTile.value.value)
                        nextTile.nextPossibleValue = (null)
                        modified = true
                    } else if (currentTile?.nextPossibleValue != null && nextTile?.nextPossibleValue == currentTile.nextPossibleValue) {
                        val newValue =
                            currentTile.nextPossibleValue?.plus(nextTile?.nextPossibleValue ?: 0)
                        currentTile.nextPossibleValue = (newValue)
                        if (newValue != null) {
                            currentScore += newValue
                        }
                        nextTile?.nextPossibleValue = (null)
                        modified = true
                        break
                    } else if (nextTile?.nextPossibleValue != null && nextTile.nextPossibleValue != currentTile?.nextPossibleValue) {
                        break
                    }
                }
            }
        }
        updateGameGrid(modified)
        if (modified) {
            addNewField()
        }
    }

    override fun swipeLeft() {
        if (_hasLost.value == true) {
            return
        }
        var modified = false
        for (i in 0 until gridSize) {
            for (j in 0 until gridSize) {
                for (k in j + 1 until gridSize) {
                    val currentTile = gameGrid.tiles[i][j]
                    val nextTile = gameGrid.tiles[i][k]
                    if (currentTile?.nextPossibleValue == null && nextTile?.nextPossibleValue != null) {
                        currentTile?.nextPossibleValue = nextTile.value.value
                        nextTile.nextPossibleValue = null
                        modified = true
                    } else if (currentTile?.nextPossibleValue != null && nextTile?.nextPossibleValue == currentTile.nextPossibleValue) {
                        val newValue =
                            currentTile.nextPossibleValue?.plus(nextTile?.nextPossibleValue ?: 0)
                        currentTile.nextPossibleValue = newValue
                        if (newValue != null) {
                            currentScore += newValue
                        }
                        nextTile?.nextPossibleValue = null
                        modified = true
                        break
                    } else if (nextTile?.nextPossibleValue != null && nextTile.nextPossibleValue != currentTile?.nextPossibleValue) {
                        break
                    }
                }
            }
        }
        updateGameGrid(modified)
        if (modified) {
            addNewField()
        }
    }

    override fun swipeUp() {
        if (_hasLost.value == true) {
            return
        }
        var modified = false
        for (i in 0 until gridSize) {
            for (j in 0 until gridSize) {
                for (k in j + 1 until gridSize) {
                    val currentTile = gameGrid.tiles[j][i]
                    val nextTile = gameGrid.tiles[k][i]
                    if (currentTile?.nextPossibleValue == null && nextTile?.nextPossibleValue != null) {
                        currentTile?.nextPossibleValue = nextTile.value.value
                        nextTile.nextPossibleValue = null
                        modified = true
                    } else if (currentTile?.nextPossibleValue != null && nextTile?.nextPossibleValue == currentTile.nextPossibleValue) {
                        val newValue =
                            currentTile.nextPossibleValue?.plus(nextTile?.nextPossibleValue ?: 0)
                        currentTile.nextPossibleValue = newValue
                        if (newValue != null) {
                            currentScore += newValue
                        }
                        nextTile?.nextPossibleValue = null
                        modified = true
                        break
                    } else if (nextTile?.nextPossibleValue != null && nextTile.nextPossibleValue != currentTile?.nextPossibleValue) {
                        break
                    }
                }
            }
        }
        updateGameGrid(modified)
        if (modified) {
            addNewField()
        }
    }

    override fun swipeDown() {
        if (_hasLost.value == true) {
            return
        }
        var modified = false
        for (i in 0 until gridSize) {
            for (j in gridSize - 1 downTo 0) {
                for (k in j - 1 downTo 0) {
                    val currentTile = gameGrid.tiles[j][i]
                    val nextTile = gameGrid.tiles[k][i]
                    if (currentTile?.nextPossibleValue == null && nextTile?.nextPossibleValue != null) {
                        currentTile?.nextPossibleValue = nextTile.value.value
                        nextTile.nextPossibleValue = null
                        modified = true
                    } else if (currentTile?.nextPossibleValue != null && nextTile?.nextPossibleValue == currentTile.nextPossibleValue) {
                        val newValue =
                            currentTile.nextPossibleValue?.plus(nextTile?.nextPossibleValue ?: 0)
                        currentTile.nextPossibleValue = newValue
                        if (newValue != null) {
                            currentScore += newValue
                        }
                        nextTile?.nextPossibleValue = null
                        modified = true
                        break
                    } else if (nextTile?.nextPossibleValue != null && nextTile.nextPossibleValue != currentTile?.nextPossibleValue) {
                        break
                    }
                }
            }
        }
        updateGameGrid(modified)
        if (modified) {
            addNewField()
        }
    }

    override fun getCurrentScore(): Int {
        return currentScore
    }

    override fun newGame(): GameGrid {
        _hasWon.value = false
        _hasLost.value = false
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

    override fun hasWon(): LiveData<Boolean> {
        return hasWon
    }

    override fun hasLost(): LiveData<Boolean> {
        return hasLost
    }

    override fun keepGoing() {
        _hasWon.value = false
    }

    private fun addNewField() {
        if (hasLost.value == true) {
            return
        }
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

    private fun canMove(x: Int, y: Int): Boolean {
        val currentTile = gameGrid.tiles[x][y]
        if (y >= gridSize - 1 || x >= gridSize - 1) {
            return currentTile?.getTileValue() == null
        }
        val rightTile = gameGrid.tiles[x][y + 1]
        val downTile = gameGrid.tiles[x + 1][y]
        return (currentTile?.getTileValue() == null
                || rightTile?.getTileValue() == null
                || currentTile.getTileValue() == rightTile.getTileValue()
                || downTile?.getTileValue() == null
                || currentTile.getTileValue() == downTile.getTileValue())
    }

    private fun updateGameGrid(modified: Boolean) {
        var canMove = false
        for (i in 0 until gridSize) {
            for (j in 0 until gridSize) {
                val currentTile = gameGrid.tiles[i][j]
                currentTile?.updateValue(modified)
                if (!canMove && canMove(i, j)) {
                    canMove = true
                }
                if (currentTile?.value?.value == WINING_VALUE) {
                    _hasWon.value = true
                }
            }
        }
        _hasLost.value = !canMove
    }

    companion object {
        private const val WINING_VALUE = 2048
    }
}