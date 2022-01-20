package com.eton.ui.feature.notes.util

import com.eton.model.Note
import com.eton.util.NoteOrder
import com.eton.util.OrderType.Ascending

/**
 * UI state: content to display in NotesScreen
 */

data class NotesState(
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(orderType = Ascending),
    val isOrderSectionVisible: Boolean = false
)









