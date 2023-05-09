package com.task.noteapp.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NoteRepositoryModule {

    @Binds
    abstract fun bindNoteRepository(
        noteRepositoryImpl: NoteRepositoryImpl,
    ): NoteRepository
}