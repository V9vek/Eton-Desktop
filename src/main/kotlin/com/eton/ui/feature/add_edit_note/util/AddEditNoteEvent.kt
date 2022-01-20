package com.eton.ui.feature.add_edit_note.util

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {
    data class EnteredTitle(val value: String) : AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditNoteEvent()
    data class EnteredContent(val value: String) : AddEditNoteEvent()
    data class ChangeContentFocus(val focusState: FocusState) : AddEditNoteEvent()
    data class ChangeNoteColor(val color: Int) : AddEditNoteEvent()
    object InsertNote : AddEditNoteEvent()
    object UpdateNote : AddEditNoteEvent()
}
