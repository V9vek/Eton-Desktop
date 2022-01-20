package com.eton.ui.feature.add_edit_note.util

sealed class AddEditMode {
    object AddMode: AddEditMode()
    object EditMode: AddEditMode()
}
