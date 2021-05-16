package com.fabinpaul.play2048.ui.game

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
        binding.lifecycleOwner = this
        setSupportActionBar(binding.toolbar)

        setUpGrid()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_game, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.newGame -> {
                viewModel.newGame()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    private fun setUpGrid() {
        viewModel.gameGrid.observe(this, { gameGrid ->
            if (binding.glGameGrid.childCount > 0) {
                binding.glGameGrid.removeAllViews()
            }
            for (i in 0 until gameGrid.size) {
                for (j in 0 until gameGrid.size) {
                    val itemBinding =
                        ItemGridCellBinding.inflate(layoutInflater, binding.glGameGrid, false)
                    itemBinding.tile = gameGrid.tiles[i][j]
                    binding.glGameGrid.addView(itemBinding.root)
                    itemBinding.lifecycleOwner = this
                }
            }
        })
    }

}