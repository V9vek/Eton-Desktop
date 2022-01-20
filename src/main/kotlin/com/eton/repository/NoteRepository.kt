package com.eton.repository

import com.eton.data_source.NoteDatabase
import com.eton.model.Note

class NoteRepository(
    private val db: NoteDatabase
) {
    fun getNotes(): List<Note> {
        return db.notes.toList()
    }

    fun getNoteById(id: Int): Note? {
        return db.notes.find { note ->
            note.id == id
        }
    }

    fun insertNote(note: Note) {
        db.notes.add(note)
    }

    fun updateNote(note: Note) {
        val existingNote = getNoteById(id = note.id)
        existingNote?.let { it ->
            val index = db.notes.indexOf(it)
            db.notes[index] = note
        }
    }

    fun deleteNote(note: Note) {
        db.notes.remove(note)
    }
}








