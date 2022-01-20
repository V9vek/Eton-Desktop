package com.eton.ui.feature.notes.util

import com.eton.util.NoteOrder

// Any Events a user can do

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    object ToggleOrderSection: NotesEvent()
}