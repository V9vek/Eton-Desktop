package com.eton.ui.feature.add_edit_note

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.eton.ui.feature.add_edit_note.components.CustomHeader
import com.eton.ui.feature.add_edit_note.components.TransparentHintTextField
import com.eton.ui.feature.add_edit_note.util.AddEditMode
import com.eton.ui.feature.add_edit_note.util.AddEditNoteEvent

@Composable
fun AddEditNoteScreen(
    viewModel: AddEditNoteViewModel,
    onBackClicked: () -> Unit
) {
    val selectedColor = viewModel.noteColor.value
    val title = viewModel.noteTitle.value
    val content = viewModel.noteContent.value
    val addEditMode = viewModel.addEditMode.value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (addEditMode == AddEditMode.AddMode) {
                        viewModel.onEvent(event = AddEditNoteEvent.InsertNote)
                    } else {
                        viewModel.onEvent(event = AddEditNoteEvent.UpdateNote)
                    }
                    onBackClicked()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .size(50.dp)
            ) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "Save note")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            CustomHeader(
                noteColor = Color(selectedColor),
                onChangeNoteColor = {
                    viewModel.onEvent(event = AddEditNoteEvent.ChangeNoteColor(color = it.toArgb()))
                },
                onBackPress = onBackClicked
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Text Fields
            TransparentHintTextField(
                text = title.text,
                hint = title.hint,
                onValueChange = {
                    viewModel.onEvent(event = AddEditNoteEvent.EnteredTitle(value = it))
                },
                onFocusChange = {
                    viewModel.onEvent(event = AddEditNoteEvent.ChangeTitleFocus(focusState = it))
                },
                isHintVisible = title.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = content.text,
                hint = content.hint,
                onValueChange = {
                    viewModel.onEvent(event = AddEditNoteEvent.EnteredContent(value = it))
                },
                onFocusChange = {
                    viewModel.onEvent(event = AddEditNoteEvent.ChangeContentFocus(focusState = it))
                },
                isHintVisible = content.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}