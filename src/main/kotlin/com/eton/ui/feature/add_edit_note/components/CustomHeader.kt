package com.eton.ui.feature.add_edit_note.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eton.model.Note

const val circleSize = 72

@Composable
fun CustomHeader(
    noteColor: Color,
    onChangeNoteColor: (Color) -> Unit,
    onBackPress: () -> Unit
) {
    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .padding(bottom = (circleSize / 2).dp)
                .background(
                    animateColorAsState(
                        targetValue = noteColor,
                        animationSpec = tween(durationMillis = 500)
                    ).value
                )
        ) {
            IconButton(onClick = onBackPress) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIos,
                    contentDescription = "Back",
                    modifier = Modifier.size(16.dp).align(Alignment.CenterStart),
                    tint = MaterialTheme.colors.primary
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            Note.noteColors.forEach { color ->
                val elevation = animateDpAsState(if (color == noteColor) 8.dp else 0.dp).value
                Box(
                    modifier = Modifier
                        .size(circleSize.dp)
                        .border(
                            width = 10.dp,
                            color = Color.White,
                            shape = CircleShape
                        )
                        .shadow(
                            elevation = elevation,
                            shape = CircleShape
                        )
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color = color)
                        .clickable { onChangeNoteColor(color) }
                )

                Spacer(modifier = Modifier.width(64.dp))
            }
        }
    }
}





















