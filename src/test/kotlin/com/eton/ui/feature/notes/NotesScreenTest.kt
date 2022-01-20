package com.eton.ui.feature.notes

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.eton.data.repo.MyRepo
import com.eton.ui.value.R
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class NotesScreenTest {

    companion object {
        private const val FAKE_WELCOME_MSG = "Hello Desktop!"
    }

    @get:Rule
    val composeRule = createComposeRule()

    private val fakeRepo = mock<MyRepo>().apply {
        whenever(getClickedWelcomeText()).thenReturn(FAKE_WELCOME_MSG)
    }

    @Before
    fun beforeEvery() {
        composeRule.setContent {
            NotesScreen(
                NotesViewModel(fakeRepo),
                navigateToAddEditNoteScreen = {}
            )
        }
    }

    @Test
    fun `Click changes the text`() {
        runBlocking(Dispatchers.Main) {
            composeRule.onNodeWithText(NotesViewModel.INIT_WELCOME_MSG).assertExists()
            composeRule.onNodeWithText(R.string.ACTION_MAIN_CLICK_ME).performClick()
            composeRule.awaitIdle()
            composeRule.onNodeWithText(FAKE_WELCOME_MSG).assertExists()
        }
    }

}