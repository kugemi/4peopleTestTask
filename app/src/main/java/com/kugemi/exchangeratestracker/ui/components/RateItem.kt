package com.kugemi.exchangeratestracker.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.kugemi.exchangeratestracker.data.enums.resources.*
import com.kugemi.exchangeratestracker.data.resources.*
import com.kugemi.exchangeratestracker.model.local_dto.FavoriteRate
import com.kugemi.exchangeratestracker.viewmodels.FavoriteRatesViewModel

@Composable
fun RateItem(
    name: String,
    value: String,
    favoriteRatesViewModel: FavoriteRatesViewModel
) {
    val favoriteRates = favoriteRatesViewModel.favoriteRates.observeAsState()

    var isSelected by remember { mutableStateOf(false) }

    favoriteRates.value?.let { favoritesList ->
        favoritesList.forEach { favorite ->
            if (name == favorite.name) isSelected = true
        }
    }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(defaultPadding),
        colors = CardDefaults.cardColors(
            containerColor =  Color.White,
        ),
    ) {
        Row(
            modifier = Modifier.padding(defaultPadding)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = name,
                color = rateNameColor,
                fontSize = fontSizeLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.weight(2f),
                text = value,
                color = Color.Black,
                fontSize = fontSizeLarge
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                    .weight(1f),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    modifier = Modifier.size(iconSize),
                    onClick = {
                        if (!isSelected) {
                            favoriteRatesViewModel.addFavoriteRate(FavoriteRate().apply {
                                this.name = name
                            })
                        } else {
                            favoriteRatesViewModel.removeFavoriteRate(name)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        tint = if (isSelected) iconStarColor else Color.Black,
                        contentDescription = "contentDescription",
                    )
                }
            }
        }
    }
}