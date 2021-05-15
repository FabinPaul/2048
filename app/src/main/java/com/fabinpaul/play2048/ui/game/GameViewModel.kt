package com.fabinpaul.play2048.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabinpaul.play2048.logic.GameGrid
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.fabinpaul.play2048.logic.Game2048

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameImpl: Game2048
) : ViewModel() {

    private val _gameGrid = MutableLiveData(gameImpl.newGame())
    val gameGrid: LiveData<GameGrid>
        get() = _gameGrid

    private val _currentScore = MutableLiveData(gameImpl.getCurrentScore())
    val currentScore: LiveData<Int>
        get() = _currentScore

    fun onUpBtnClick() {
        gameImpl.swipeUp()
        _currentScore.value = gameImpl.getCurrentScore()
    }

    fun onDownBtnClick() {
        gameImpl.swipeDown()
        _currentScore.value = gameImpl.getCurrentScore()
    }

    fun onLeftBtnClick() {
        gameImpl.swipeLeft()
        _currentScore.value = gameImpl.getCurrentScore()
    }

    fun onRightBtnClick() {
        gameImpl.swipeRight()
        _currentScore.value = gameImpl.getCurrentScore()
    }

    fun newGame() {
        _gameGrid.value = gameImpl.newGame()
        _currentScore.value = gameImpl.getCurrentScore()
    }

    companion object {
        const val GRID_SIZE = 4
    }
}