package com.eton.ui.feature.notes

import com.github.theapache64.expekt.should
import com.eton.data.repo.MyRepo
import com.eton.test.MyDaggerMockRule
import it.cosenonjaviste.daggermock.InjectFromComponent
import org.junit.Rule
import org.junit.Test

class NotesViewModelTest {

    @get:Rule
    val daggerMockRule = MyDaggerMockRule()

    @InjectFromComponent
    private lateinit var myRepo: MyRepo

    private val notesViewModel by lazy {
        NotesViewModel(myRepo)
    }

    @Test
    fun `Button click changes the welcome text`() {
        notesViewModel.welcomeText.value.should.equal(NotesViewModel.INIT_WELCOME_MSG)
        notesViewModel.onClickMeClicked()
        notesViewModel.welcomeText.value.should.equal(myRepo.getClickedWelcomeText())
    }
}