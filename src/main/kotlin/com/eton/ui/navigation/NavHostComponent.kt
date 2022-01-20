package com.eton.ui.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.*
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.animation.child.crossfade
import com.arkivanov.decompose.statekeeper.Parcelable
import com.eton.di.AppComponent
import com.eton.di.DaggerAppComponent
import com.eton.model.Note
import com.eton.ui.feature.add_edit_note.AddEditNoteScreenComponent
import com.eton.ui.feature.notes.NotesScreenComponent
import com.eton.ui.feature.splash.SplashScreenComponent

/**
 * All navigation decisions are made from here
 */
class NavHostComponent(
    private val componentContext: ComponentContext,
) : Component, ComponentContext by componentContext {

    /**
     * Available screensSelectApp
     */
    private sealed class Config : Parcelable {
        object Splash : Config()
        object Notes : Config()
        data class AddEditNote(
            val selectedColor: Int,
            val note: Note?
        ) : Config()
    }

    private val appComponent: AppComponent = DaggerAppComponent
        .create()

    /**
     * Router configuration
     */
    private val router = router<Config, Component>(
        initialConfiguration = Config.Splash,
        childFactory = ::createScreenComponent
    )

    /**
     * When a new navigation request made, the screen will be created by this method.
     */
    private fun createScreenComponent(config: Config, componentContext: ComponentContext): Component {
        return when (config) {
            is Config.Splash -> SplashScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext,
                onSplashFinished = ::onSplashFinished,
            )
            is Config.Notes -> NotesScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext,
                navigateToAddEditNoteScreen = { selectedColor, note ->
                    router.push(configuration = Config.AddEditNote(selectedColor = selectedColor, note = note))
                }
            )
            is Config.AddEditNote -> AddEditNoteScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext,
                noteColor = config.selectedColor,
                note = config.note,
                onBackClicked = ::onBackClicked
            )
        }
    }

    @Composable
    override fun render() {
        Children(
            routerState = router.state,
            animation = crossfade()
        ) { child ->
            child.instance.render()
        }
    }

    /**
     * Invoked when splash finish data sync
     */
    private fun onSplashFinished() {
        router.replaceCurrent(Config.Notes)
    }

    /**
     * Invoked when back button is pressed
     */
    private fun onBackClicked() {
        router.pop()
    }
}