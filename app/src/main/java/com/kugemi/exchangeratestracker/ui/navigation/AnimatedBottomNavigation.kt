package com.kugemi.exchangeratestracker.ui.navigation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedBottomNavigation(
    modifier: Modifier = Modifier,
    tabs: Array<MainScreenTab> = MainScreenTab.values(),
    selectedTab: MainScreenTab,
    onItemClick: (tab:Int) -> Unit,
) {
    val configuration = LocalConfiguration.current

    Box {
        var width by remember { mutableStateOf(0f) }
        var currentIndex by remember { mutableStateOf(0) }
        val offsetAnim by animateFloatAsState(
            targetValue = when (currentIndex) {
                1 -> width / 2f
                else -> 0f
            }
        )
        BottomNavigation(
            modifier = modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    width = it.size.width.toFloat()
                },
            backgroundColor = Color(0xFF9796f0)
        ) {
            tabs.forEachIndexed { index, tab ->
                AnimatedBottomNavigationItem(
                    label = tab.title,
                    icon = tab.icon,
                    tabSize = configuration.screenWidthDp.dp / 2,
                    selected = tab == selectedTab,
                    onClick = {
                        onItemClick(tab.title)
                        currentIndex = index
                    }
                )
            }
        }
        Box(
            modifier = Modifier
                .width(configuration.screenWidthDp.dp / 2)
                .height(3.dp)
                .offset(with(LocalDensity.current) { offsetAnim.toDp() }, 0.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Color.White)
        )
    }
}

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = contentColorFor(backgroundColor),
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        modifier = modifier
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = content
        )
    }
}