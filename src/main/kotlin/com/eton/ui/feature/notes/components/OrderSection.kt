package com.eton.ui.feature.notes.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eton.util.NoteOrder
import com.eton.util.OrderType.Ascending
import com.eton.util.OrderType.Descending

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(orderType = Descending),
    onOrderChange: (NoteOrder) -> Unit
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Title",
                isSelected = noteOrder is NoteOrder.Title,
                onSelect = { onOrderChange(NoteOrder.Title(orderType = noteOrder.orderType)) }
            )
            Spacer(Modifier.width(16.dp))
            DefaultRadioButton(
                text = "Date",
                isSelected = noteOrder is NoteOrder.Date,
                onSelect = { onOrderChange(NoteOrder.Date(orderType = noteOrder.orderType)) }
            )
            Spacer(Modifier.width(16.dp))
            DefaultRadioButton(
                text = "Color",
                isSelected = noteOrder is NoteOrder.Color,
                onSelect = { onOrderChange(NoteOrder.Color(orderType = noteOrder.orderType)) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(
                text = "Ascending",
                isSelected = noteOrder.orderType is Ascending,
                onSelect = { onOrderChange(noteOrder.copy(orderType = Ascending)) }
            )
            Spacer(Modifier.width(16.dp))
            DefaultRadioButton(
                text = "Descending",
                isSelected = noteOrder.orderType is Descending,
                onSelect = { onOrderChange(noteOrder.copy(orderType = Descending)) }
            )
        }
    }
}




















