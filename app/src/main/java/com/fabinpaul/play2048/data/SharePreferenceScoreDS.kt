package com.fabinpaul.play2048.data

import android.content.Context
import com.fabinpaul.play2048.R
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharePreferenceScoreDS @Inject constructor(
    @ApplicationContext val context: Context
) : ScoreDataSource {

    private val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)

    override fun getHighScore(): Int {
        return sharedPref.getInt(context.getString(R.string.saved_high_score_key), 0)
    }

    override fun saveHighScore(newHighScore: Int) {
        with(sharedPref.edit()) {
            putInt(context.getString(R.string.saved_high_score_key), newHighScore)
            apply()
        }
    }
}