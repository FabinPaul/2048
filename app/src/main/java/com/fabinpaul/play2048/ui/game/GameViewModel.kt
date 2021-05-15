package com.fabinpaul.play2048.ui.game

import androidx.lifecycle.ViewModel
import com.fabinpaul.play2048.logic.GameGrid
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {

    val gameGrid = GameGrid(size = GRID_SIZE)

    companion object {
        const val GRID_SIZE = 4
    }
}