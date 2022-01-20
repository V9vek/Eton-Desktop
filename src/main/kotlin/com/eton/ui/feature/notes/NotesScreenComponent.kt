package com.eton.ui.feature.notes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.arkivanov.decompose.ComponentContext
import com.eton.di.AppComponent
import com.eton.model.Note
import com.eton.ui.navigation.Component
import javax.inject.Inject

class NotesScreenComponent(
    appComponent: AppComponent,
    private val componentContext: ComponentContext,
    private val navigateToAddEditNoteScreen: (selectedColor: Int, note: Note?) -> Unit,
) : Component, ComponentContext by componentContext {
    @Inject
    lateinit var viewModel: NotesViewModel

    init {
        appComponent.inject(this)
    }

    @Composable
    override fun render() {
        val scope = rememberCoroutineScope()
        LaunchedEffect(viewModel) {
            viewModel.init(viewModelScope = scope)
        }

        NotesScreen(
            viewModel = viewModel,
            navigateToAddEditNoteScreen = { selectedColor, note ->
                navigateToAddEditNoteScreen(selectedColor, note)
            }
        )
    }
}












