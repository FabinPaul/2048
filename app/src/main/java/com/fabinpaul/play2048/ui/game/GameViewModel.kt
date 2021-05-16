package com.fabinpaul.play2048.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabinpaul.play2048.data.ScoreDataSource
import com.fabinpaul.play2048.logic.GameGrid
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.fabinpaul.play2048.logic.Game2048

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameImpl: Game2048,
    private val scoreDataSource: ScoreDataSource
) : ViewModel() {

    private val _gameGrid = MutableLiveData(gameImpl.newGame())
    val gameGrid: LiveData<GameGrid>
        get() = _gameGrid

    private val _currentScore = MutableLiveData(gameImpl.getCurrentScore())
    val currentScore: LiveData<Int>
        get() = _currentScore

    private val _highScore = MutableLiveData(scoreDataSource.getHighScore())
    val highScore: LiveData<Int>
        get() = _highScore

    val hasLost: LiveData<Boolean>
        get() = gameImpl.hasLost()

    val hasWon: LiveData<Boolean>
        get() = gameImpl.hasWon()


    fun onUpBtnClick() {
        gameImpl.swipeUp()
        updateScore()

    }

    fun onDownBtnClick() {
        gameImpl.swipeDown()
        updateScore()
    }

    fun onLeftBtnClick() {
        gameImpl.swipeLeft()
        updateScore()
    }

    fun onRightBtnClick() {
        gameImpl.swipeRight()
        updateScore()
    }

    fun newGame() {
        _gameGrid.value = gameImpl.newGame()
        updateScore()
    }

    fun keepGoing() {
        gameImpl.keepGoing()
    }

    private fun updateScore() {
        val currentScore = gameImpl.getCurrentScore()
        _currentScore.value = currentScore
        highScore.value?.let {
            if (currentScore > it) {
                _highScore.value = currentScore
                scoreDataSource.saveHighScore(currentScore)
            }
        }
    }

    companion object {
        const val GRID_SIZE = 4
    }
}