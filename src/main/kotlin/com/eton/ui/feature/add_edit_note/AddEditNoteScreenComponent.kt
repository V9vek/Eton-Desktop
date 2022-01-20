package com.eton.ui.feature.add_edit_note

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.arkivanov.decompose.ComponentContext
import com.eton.di.AppComponent
import com.eton.model.Note
import com.eton.ui.navigation.Component
import javax.inject.Inject

class AddEditNoteScreenComponent(
    appComponent: AppComponent,
    private val componentContext: ComponentContext,
    private val noteColor: Int,
    private val note: Note?,
    private val onBackClicked: () -> Unit,
) : Component, ComponentContext by componentContext {
    @Inject
    lateinit var viewModel: AddEditNoteViewModel

    init {
        appComponent.inject(this)
    }

    @Composable
    override fun render() {
        val scope = rememberCoroutineScope()
        LaunchedEffect(viewModel) {
            viewModel.init(
                viewModelScope = scope,
                noteColor = noteColor,
                note = note
            )
        }

        AddEditNoteScreen(
            viewModel = viewModel,
            onBackClicked = onBackClicked
        )
    }
}













