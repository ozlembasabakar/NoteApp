package com.example.noteapp.di

import android.app.Application
import androidx.room.Room
import com.example.noteapp.featurenote.data.datasource.NoteDatabase
import com.example.noteapp.featurenote.data.repository.NoteRepositoryImpl
import com.example.noteapp.featurenote.domain.repository.NoteRepository
import com.example.noteapp.featurenote.domain.usecase.DeleteNote
import com.example.noteapp.featurenote.domain.usecase.GetNotes
import com.example.noteapp.featurenote.domain.usecase.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabase: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(noteDatabase.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(noteRepository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            GetNotes(noteRepository),
            DeleteNote(noteRepository)
        )
    }
}

