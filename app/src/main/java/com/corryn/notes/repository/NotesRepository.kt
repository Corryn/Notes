package com.corryn.notes.repository

import com.corryn.notes.database.NotesDao
import com.corryn.notes.mapper.toModel
import com.corryn.notes.model.NoteModel
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val notesDao: NotesDao
) {

    suspend fun getNotes(): List<NoteModel> {
        return notesDao.getNotes().map {
            it.toModel()
        }
    }

}