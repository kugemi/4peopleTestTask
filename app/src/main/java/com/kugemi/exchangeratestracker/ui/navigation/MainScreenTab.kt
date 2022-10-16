package com.kugemi.exchangeratestracker.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.kugemi.exchangeratestracker.R

enum class MainScreenTab(
    @StringRes val title: Int,
    val icon: ImageVector
) {
    HOME(R.string.home_tab, Icons.Filled.Home),
    FAVORITES(R.string.favorites_tab, Icons.Filled.Favorite);

    companion object {
        fun getTabByResource(@StringRes title: Int): MainScreenTab {
            return when (title) {
                R.string.home_tab -> HOME
                R.string.favorites_tab -> FAVORITES
                else -> HOME
            }
        }
    }
}