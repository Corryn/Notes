package com.corryn.notes.di

import android.content.Context
import androidx.room.Room
import com.corryn.notes.database.NotesDao
import com.corryn.notes.database.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideNotesDatabase(
        @ApplicationContext context: Context
    ): NotesDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = NotesDatabase::class.java,
            name = NotesDatabase.NOTES_DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideNotesDao(
        database: NotesDatabase
    ): NotesDao {
        return database.notesDao()
    }

}