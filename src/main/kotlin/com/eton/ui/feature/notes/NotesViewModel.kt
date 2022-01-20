package com.eton.ui.feature.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.eton.model.Note
import com.eton.repository.NoteRepository
import com.eton.ui.feature.notes.util.NotesEvent
import com.eton.ui.feature.notes.util.NotesState
import com.eton.util.NoteOrder
import com.eton.util.OrderType
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class NotesViewModel @Inject constructor(
    private val repository: NoteRepository
) {
    private lateinit var viewModelScope: CoroutineScope

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    fun init(
        viewModelScope: CoroutineScope
    ) {
        this.viewModelScope = viewModelScope

        getNotes(noteOrder = NoteOrder.Date(orderType = OrderType.Descending))
    }

    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                // check if same order and order type clicked multiple times
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }

                getNotes(noteOrder = event.noteOrder)
            }

            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        val notes = repository.getNotes()

        _state.value = state.value.copy(
            notes = filterNotes(notes, noteOrder),
            noteOrder = noteOrder
        )
    }

    // TODO: make use_case for this
    private fun filterNotes(
        notes: List<Note>,
        noteOrder: NoteOrder
    ): List<Note> {
        return when (noteOrder.orderType) {
            is OrderType.Ascending -> {
                when (noteOrder) {
                    is NoteOrder.Title -> notes.sortedBy { it.title }
                    is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                    is NoteOrder.Color -> notes.sortedBy { it.color }
                    else -> notes.sortedByDescending { it.timestamp }
                }
            }

            is OrderType.Descending -> {
                when (noteOrder) {
                    is NoteOrder.Title -> notes.sortedByDescending { it.title }
                    is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                    is NoteOrder.Color -> notes.sortedByDescending { it.color }
                    else -> notes.sortedByDescending { it.timestamp }
                }
            }

            else -> notes.sortedByDescending { it.timestamp }
        }
    }
}



















