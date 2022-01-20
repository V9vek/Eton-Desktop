package com.eton.ui.feature.notes.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.eton.ui.feature.notes.components.FabState.COLLAPSED
import com.eton.ui.feature.notes.components.FabState.EXPANDED
import com.eton.ui.feature.notes.util.FabItem

enum class FabState {
    COLLAPSED, EXPANDED
}

@Composable
fun CustomFab(
    fabState: FabState,
    onFabStateToggle: () -> Unit,
    fabItems: List<FabItem>,
    onFabItemClick: (Color) -> Unit,
    modifier: Modifier = Modifier
) {
    val transition = updateTransition(
        targetState = fabState,
        label = "Fab State"
    )

    val rotation by transition.animateFloat(label = "rotation") { state ->
        when (state) {
            COLLAPSED -> 0f
            EXPANDED -> 45f
        }
    }

    /*
    val collapsedIconSize = 48.dp
    val expandedIconSize = 50.dp
    val fabSize by transition.animateDp(
        label = "size",
        transitionSpec = {
            when {
                EXPANDED isTransitioningTo COLLAPSED -> keyframes {
                    durationMillis = 200
                    expandedIconSize at 100
                }
                else -> keyframes {
                    durationMillis = 200
                    expandedIconSize at 100
                }
            }
        }
    ) { state ->
        when (state) {
            COLLAPSED -> collapsedIconSize
            EXPANDED -> collapsedIconSize
        }
    }
    */

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FloatingActionButton(
            onClick = { onFabStateToggle() },
            modifier = modifier
                .padding(horizontal = 8.dp, vertical = 2.dp)
                .size(45.dp)
                .zIndex(1f),
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Add",
                modifier = Modifier
                    .size(28.dp)
                    .rotate(degrees = rotation)
            )
        }

        CustomFabItemList(
            fabState = fabState,
            fabItems = fabItems,
            onFabItemClick = {
                onFabItemClick(it)
            }
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CustomFabItemList(
    fabState: FabState,
    fabItems: List<FabItem>,
    onFabItemClick: (Color) -> Unit,
) {
    AnimatedVisibility(
        visible = fabState == EXPANDED,
        enter = slideInVertically(
            animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
        ) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { -it / 4 }) + fadeOut()
    ) {
        Column(modifier = Modifier.padding(vertical = 32.dp)) {
            fabItems.forEach { fabItem ->
                CustomFabItem(fabItem = fabItem, onFabItemClick = onFabItemClick)
            }
        }
    }
}

@Composable
fun CustomFabItem(
    fabItem: FabItem,
    onFabItemClick: (Color) -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .size(26.dp)
            .background(color = fabItem.color)
            .clickable { onFabItemClick(fabItem.color) }
    )
    Spacer(modifier = Modifier.height(12.dp))
}



















