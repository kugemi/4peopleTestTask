package com.kugemi.exchangeratestracker.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kugemi.exchangeratestracker.data.resources.bottomBarHeight
import com.kugemi.exchangeratestracker.data.resources.bottomBarIconSize
import com.kugemi.exchangeratestracker.data.resources.bottomBarPadding
import com.kugemi.exchangeratestracker.data.resources.fontBottomBar

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AnimatedBottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    tabSize: Dp,
    label: Int,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val top by animateDpAsState(
        targetValue = if (selected) 0.dp else bottomBarHeight,
        animationSpec = SpringSpec(dampingRatio = 0.5f, stiffness = 200f)
    )
    Box(
        modifier = Modifier
            .width(tabSize)
            .height(bottomBarHeight)
            .padding(start = bottomBarPadding, end = bottomBarPadding)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick.invoke()
            },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            tint = Color.White,

            contentDescription = null,
            modifier = Modifier
                .height(bottomBarHeight)
                .width(bottomBarIconSize)
                .offset(y = top)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(bottomBarHeight)
                .offset(y = top - bottomBarHeight)
        ) {
            Text(
                text = stringResource(id = label),
                fontSize = fontBottomBar,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}
