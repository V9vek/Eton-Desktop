package com.eton.ui.feature.notes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.eton.model.Note
import com.eton.ui.feature.notes.util.convertLongToTime

@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 18.dp,
    onEditClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(260.dp)
            .padding(14.dp)
            .clip(RoundedCornerShape(cornerRadius))
            .background(color = Color(note.color)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(end = 32.dp)
            ) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.h6,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = note.content,
                    style = MaterialTheme.typography.body1,
                    color = Color.Black,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // bottom row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // timestamp
                Text(
                    text = convertLongToTime(note.timestamp),
                    color = Color.Black,
                    style = MaterialTheme.typography.body1,
                    fontStyle = FontStyle.Italic
                )

                IconButton(
                    onClick = { onEditClick() },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.secondary)
                        .size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = "Delete note",
                        tint = MaterialTheme.colors.onSecondary,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}





















