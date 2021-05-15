package com.fabinpaul.play2048.ui.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.fabinpaul.play2048.R
import com.fabinpaul.play2048.databinding.ActivityGameBinding
import com.fabinpaul.play2048.databinding.ItemGridCellBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game)
        binding.viewModel = viewModel
        setSupportActionBar(binding.toolbar)

        setUpGrid()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_game, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setUpGrid() {
        for (i in 0 until GameViewModel.GRID_SIZE) {
            for (j in 0 until GameViewModel.GRID_SIZE) {
                val itemBinding =
                    ItemGridCellBinding.inflate(layoutInflater, binding.glGameGrid, false)
                itemBinding.tile = viewModel.gameGrid.tiles[i][j]
                binding.glGameGrid.addView(itemBinding.root)
            }
        }
    }

}