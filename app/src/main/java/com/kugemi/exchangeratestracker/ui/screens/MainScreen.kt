package com.kugemi.exchangeratestracker.ui.screens

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kugemi.exchangeratestracker.ui.navigation.AnimatedBottomNavigation
import com.kugemi.exchangeratestracker.ui.navigation.MainScreenTab
import com.kugemi.exchangeratestracker.viewmodels.ExchangeRatesViewModel
import com.kugemi.exchangeratestracker.viewmodels.FavoriteRatesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreeen(
    exchangeViewModel: ExchangeRatesViewModel = viewModel(),
    favoriteRatesViewModel: FavoriteRatesViewModel = viewModel()
) {
    var selectedTab by remember { mutableStateOf(MainScreenTab.getTabByResource(0)) }

    Scaffold(
        bottomBar = {
            AnimatedBottomNavigation(
                selectedTab = selectedTab,
                onItemClick = {selectedTab = MainScreenTab.getTabByResource(it)}
            )
        }
    ) { paddingValues ->
        val modifier = Modifier.padding(paddingValues)
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF7BC6CC),
                            Color(0xFFBE93C5)

                        )
                    )
                )
                .fillMaxSize()
        ) {
            Crossfade(

                targetState = selectedTab
            ) { tab ->
                when (tab) {
                    MainScreenTab.HOME -> HomeScreen(
                        exchangeViewModel = exchangeViewModel,
                        favoriteRatesViewModel = favoriteRatesViewModel
                    )
                    MainScreenTab.FAVORITES -> FavoritesScreen(
                        exchangeViewModel = exchangeViewModel,
                        favoriteRatesViewModel = favoriteRatesViewModel
                    )
                }
            }
        }

    }
}