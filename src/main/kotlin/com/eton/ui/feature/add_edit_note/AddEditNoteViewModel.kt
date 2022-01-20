package com.eton.ui.feature.add_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.eton.model.Note
import com.eton.repository.NoteRepository
import com.eton.ui.feature.add_edit_note.util.AddEditMode
import com.eton.ui.feature.add_edit_note.util.AddEditNoteEvent
import com.eton.ui.feature.add_edit_note.util.NoteTextFieldState
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class AddEditNoteViewModel @Inject constructor(
    private val repository: NoteRepository
) {
    private lateinit var viewModelScope: CoroutineScope

    private val _noteColor = mutableStateOf(-1)
    val noteColor: State<Int> = _noteColor

    private val _noteTitle = mutableStateOf(NoteTextFieldState(hint = "Enter Title"))
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(NoteTextFieldState(hint = "Enter Content"))
    val noteContent: State<NoteTextFieldState> = _noteContent

    private val _addEditMode = mutableStateOf<AddEditMode>(AddEditMode.AddMode)
    val addEditMode: State<AddEditMode> = _addEditMode

    private var clickedNoteId = -1

    fun init(
        viewModelScope: CoroutineScope,
        noteColor: Int,
        note: Note?
    ) {
        this.viewModelScope = viewModelScope

        _noteColor.value = noteColor

        note?.let {
            clickedNoteId = it.id
            _noteColor.value = it.color
            _noteTitle.value = NoteTextFieldState(text = it.title)
            _noteContent.value = NoteTextFieldState(text = it.content)
            _addEditMode.value = AddEditMode.EditMode   // changing add or edit mode
        }
    }

    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.ChangeNoteColor -> {
                _noteColor.value = event.color
            }
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(text = event.value)
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteTitle.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = noteContent.value.copy(text = event.value)
            }
            is AddEditNoteEvent.ChangeContentFocus -> {
                _noteContent.value = noteContent.value.copy(
                    isHintVisible = !event.focusState.isFocused && noteContent.value.text.isBlank()
                )
            }
            is AddEditNoteEvent.InsertNote -> {
                repository.insertNote(
                    note = Note(
                        title = noteTitle.value.text,
                        content = noteContent.value.text,
                        timestamp = System.currentTimeMillis(),
                        color = noteColor.value
                    )
                )
            }
            is AddEditNoteEvent.UpdateNote -> {
                repository.updateNote(
                    note = Note(
                        title = noteTitle.value.text,
                        content = noteContent.value.text,
                        timestamp = System.currentTimeMillis(),
                        color = noteColor.value,
                        id = clickedNoteId
                    )
                )
            }
        }
    }
}
















