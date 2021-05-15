package com.fabinpaul.play2048.logic

import androidx.lifecycle.MutableLiveData
import com.fabinpaul.play2048.R
import kotlin.math.ln

data class Tile(
    val x: Int = 0,
    val y: Int = 0,
    val value: MutableLiveData<Int?> = MutableLiveData<Int?>(null),
    val bgColor: MutableLiveData<Int> = MutableLiveData<Int>(R.color.light_blue_30)
) {

    fun setValue(newValue: Int?) {
        value.value = newValue
        when (newValue) {
            null -> bgColor.value = R.color.light_blue_30
            else -> {
                val index = (ln(newValue.toDouble()) / ln(2.0)).toInt()
                bgColor.value = colorArray[index]
            }
        }
    }

    companion object {

        @JvmStatic
        fun valueOf(value: Int?): String {
            return if (value != null && value > 0) {
                value.toString()
            } else {
                " "
            }
        }
    }
}

val colorArray =
    arrayOf(
        R.color.light_blue_30,
        R.color.blue_37,
        R.color.light_green_96,
        R.color.purple_70,
        R.color.pink_ff,
        R.color.yellow_f2,
        R.color.light_red_ff,
        R.color.light_brown_ff
    )