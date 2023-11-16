package com.sergeevme.cleanandroid.domain.usecase

import com.sergeevme.cleanandroid.domain.models.Note
import com.sergeevme.cleanandroid.domain.models.SaveNote
import com.sergeevme.cleanandroid.domain.repository.NoteRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

class SaveNoteUseCaseTest {

    private val noteRepository = mock<NoteRepository>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(noteRepository)
    }

    @Test
    fun `should return false if text is empty`() {

        val testData = SaveNote("")
        val expected = false
        Mockito.`when`(noteRepository.saveNote(testData)).thenReturn(expected)

        val useCase = SaveNoteUseCase(noteRepository = noteRepository)
        val actual = useCase.execute(testData)

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `should return true if text was already saved`() {

        val testMockitoData = Note(1, "test text")
        Mockito.`when`(noteRepository.getNote()).thenReturn(testMockitoData)

        val useCase = SaveNoteUseCase(noteRepository = noteRepository)

        val testData = SaveNote("test text")
        val actual = useCase.execute(testData)
        val expected = true

        Assertions.assertEquals(expected, actual)
        Mockito.verify(noteRepository, Mockito.never()).saveNote(saveNote = any())
    }

    @Test
    fun `should verify that saveNote was called and text was not already saved`() {

        val testMockitoData = Note(1, "test text was not saved")
        Mockito.`when`(noteRepository.getNote()).thenReturn(testMockitoData)

        val useCase = SaveNoteUseCase(noteRepository = noteRepository)

        val testData = SaveNote("test text")
        useCase.execute(testData)

        Mockito.verify(noteRepository, Mockito.times(1)).saveNote(saveNote = testData)
    }

    @Test
    fun `should verify that saveNote was not called if text was already saved`() {

        // Mocking
        val mockGetData = Note(1, "test text")
        Mockito.`when`(noteRepository.getNote()).thenReturn(mockGetData)

        val testSaveData = SaveNote("test text")
        val expected = true
        Mockito.`when`(noteRepository.saveNote(testSaveData)).thenReturn(expected)

        // Action
        SaveNoteUseCase(noteRepository = noteRepository)

        Mockito.verify(noteRepository, Mockito.never()).saveNote(saveNote = any())
    }

}