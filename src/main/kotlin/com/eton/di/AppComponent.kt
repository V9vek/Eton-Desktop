package com.eton.di

import com.eton.ui.feature.add_edit_note.AddEditNoteScreenComponent
import com.eton.ui.feature.notes.NotesScreenComponent
import com.eton.ui.feature.splash.SplashScreenComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(splashScreenComponent: SplashScreenComponent)
    fun inject(notesScreenComponent: NotesScreenComponent)
    fun inject(addEditNoteScreenComponent: AddEditNoteScreenComponent)
}