package com.corryn.notes.mapper

import com.corryn.notes.database.entity.NoteEntity
import com.corryn.notes.model.NoteModel

fun NoteEntity.toModel(): NoteModel {
    return NoteModel(
        id = this.id,
        title = this.title,
        content = this.content,
        author = this.author
    )
}

fun NoteModel.toEntity(): NoteEntity {
    return NoteEntity(
        id = this.id,
        title = this.title,
        content = this.content,
        author = this.author
    )
}
