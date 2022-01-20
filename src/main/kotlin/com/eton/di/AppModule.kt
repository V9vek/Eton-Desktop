package com.eton.di

import com.eton.data_source.NoteDatabase
import com.eton.repository.NoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    fun provideNoteDatabase(): NoteDatabase {
        return NoteDatabase()
    }

    @Singleton
    @Provides
    fun provideNoteRepository(noteDatabase: NoteDatabase): NoteRepository {
        return NoteRepository(db = noteDatabase)
    }
}























