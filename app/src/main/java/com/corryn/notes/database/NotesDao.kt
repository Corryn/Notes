package com.corryn.notes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.corryn.notes.database.entity.NoteEntity

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")
    suspend fun getNotes(): List<NoteEntity>

    @Insert
    suspend fun insertNote(note: NoteEntity)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNoteById(id: String)

}