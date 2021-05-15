package com.fabinpaul.play2048.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.fabinpaul.play2048.logic.Game2048Impl
import com.fabinpaul.play2048.logic.Game2048
import com.fabinpaul.play2048.ui.game.GameViewModel
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@InstallIn(ViewModelComponent::class)
@Module
abstract class GameModuleInterface {

    @Binds
    abstract fun gameImplementationInstance(
        gameImpl: Game2048Impl
    ): Game2048
}

@InstallIn(SingletonComponent::class)
@Module
class GameModuleProvides {

    @Provides
    @Named("gridSize")
    fun gameGridSize(): Int {
        return GameViewModel.GRID_SIZE
    }
}