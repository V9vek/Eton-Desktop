package com.eton.ui.feature.notes

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.eton.model.Note
import com.eton.ui.feature.notes.components.CustomFab
import com.eton.ui.feature.notes.components.FabState.COLLAPSED
import com.eton.ui.feature.notes.components.FabState.EXPANDED
import com.eton.ui.feature.notes.components.NoteItem
import com.eton.ui.feature.notes.components.OrderSection
import com.eton.ui.feature.notes.util.FabItem
import com.eton.ui.feature.notes.util.NotesEvent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesScreen(
    viewModel: NotesViewModel,
    navigateToAddEditNoteScreen: (selectedColor: Int, note: Note?) -> Unit,
) {
    val state = viewModel.state.value
    var fabState by remember { mutableStateOf(COLLAPSED) }

    Box(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            // sidebar
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomFab(
                    fabState = fabState,
                    onFabStateToggle = {
                        fabState = if (fabState == COLLAPSED) EXPANDED else COLLAPSED
                    },
                    fabItems = Note.noteColors.map { FabItem(color = it) },
                    onFabItemClick = { color ->
                        navigateToAddEditNoteScreen(color.toArgb(), null)
                    },
                )
            }

            Divider(
                modifier = Modifier.fillMaxHeight().width(1.dp),
                color = Color.Gray.copy(alpha = 0.5f),
            )

            // list and other content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Notes",
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.SemiBold
                    )
                    IconButton(
                        onClick = { viewModel.onEvent(event = NotesEvent.ToggleOrderSection) },
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterAlt,
                            contentDescription = "Filter Notes List"
                        )
                    }
                }

                // Filter Section
                AnimatedVisibility(
                    visible = state.isOrderSectionVisible,
                    enter = slideInVertically(initialOffsetY = { -it / 3 }) + fadeIn(),
                    exit = slideOutVertically(targetOffsetY = { -it / 3 }) + fadeOut()
                ) {
                    OrderSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        noteOrder = state.noteOrder,
                        onOrderChange = { noteOrder ->
                            viewModel.onEvent(event = NotesEvent.Order(noteOrder = noteOrder))
                        }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Notes List
                LazyVerticalGrid(
                    cells = GridCells.Fixed(count = 3),
                    modifier = Modifier
                        .fillMaxSize()
                        .animateContentSize()
                ) {
                    items(state.notes) { note ->
                        NoteItem(
                            note = note,
                            modifier = Modifier.fillMaxWidth(),
                            onEditClick = {
                                navigateToAddEditNoteScreen(-1, note)
                            }
                        )
                    }
                }
            }
        }
    }
}