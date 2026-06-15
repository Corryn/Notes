package com.corryn.notes.model

/**
 * Domain model representing the contents of a note.
 */
data class NoteModel(
    val id: String,
    val title: String,
    val content: String,
    val author: String,
)
