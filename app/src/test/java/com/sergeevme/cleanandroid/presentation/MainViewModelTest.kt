package com.sergeevme.cleanandroid.presentation

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.sergeevme.cleanandroid.domain.models.Note
import com.sergeevme.cleanandroid.domain.models.SaveNote
import com.sergeevme.cleanandroid.domain.usecase.GetNoteUseCase
import com.sergeevme.cleanandroid.domain.usecase.SaveNoteUseCase
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito
import org.mockito.kotlin.mock

class MainViewModelTest {

    private val getNoteUseCase = mock<GetNoteUseCase>()
    private val saveNoteUseCase = mock<SaveNoteUseCase>()
    private lateinit var viewModel: MainViewModel

    @AfterEach
    fun afterEach() {
        Mockito.reset(getNoteUseCase)
        Mockito.reset(saveNoteUseCase)
        ArchTaskExecutor.getInstance().setDelegate(null)
    }

    @BeforeEach
    fun beforeEach() {
        viewModel = MainViewModel(
            getNoteUseCase = getNoteUseCase,
            saveNoteUseCase = saveNoteUseCase
        )

        // We can use "androidx.arch.core:core-testing" library to pass the error with Looper
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) { runnable.run() }
            override fun postToMainThread(runnable: Runnable) { runnable.run() }
            override fun isMainThread(): Boolean { return true }
        })
    }

    companion object {
        @JvmStatic
        fun textSaving() = listOf(
            Arguments.of(true, "Saved"),
            Arguments.of(false, "Not Saved")
        )
    }

    @ParameterizedTest
    @MethodSource("textSaving")
    fun `should save text and return true`(input: Boolean, expected: String) {

        val testSaveText = "test text"
        val testParams = SaveNote(text = testSaveText)

        Mockito.`when`(saveNoteUseCase.execute(testParams)).thenReturn(input)

        viewModel.save(text = testSaveText)

        val actual = viewModel.lastActionLive.value

        Mockito.verify(saveNoteUseCase, Mockito.times(1)).execute(testParams)
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `should load text`() {

        val testNote = Note(1 ,"test note")
        Mockito.`when`(getNoteUseCase.execute()).thenReturn(testNote)

        viewModel.load()

        val expected = testNote.text
        val actual = viewModel.loadLive.value

        Mockito.verify(getNoteUseCase, Mockito.times(1)).execute()
        Assertions.assertEquals(expected, actual)
    }

}