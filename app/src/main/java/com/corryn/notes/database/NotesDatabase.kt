package com.corryn.notes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.corryn.notes.database.entity.NoteEntity

@Database(
    version = 1,
    entities = [NoteEntity::class]
)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun notesDao(): NotesDao

    companion object {
        const val NOTES_DATABASE_NAME = "notes-database"
    }

}