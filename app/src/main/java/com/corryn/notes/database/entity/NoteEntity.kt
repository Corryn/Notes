package com.corryn.notes.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Representation of a note in the database.
 */
@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val content: String,
    val author: String,
)
