package com.sergeevme.cleanandroid.domain.usecase

import com.sergeevme.cleanandroid.domain.models.Note
import com.sergeevme.cleanandroid.domain.repository.NoteRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetNoteUseCaseTest {

    private val noteRepository = mock<NoteRepository>()

    //@Mock
    //lateinit var noteRepository: NoteRepository

    @Test
    fun `use case should return the same data as in repository`() {

        val testData = Note(1, "test text")
        Mockito.`when`(noteRepository.getNote()).thenReturn(testData)

        val useCase = GetNoteUseCase(noteRepository = noteRepository)

        val actual = useCase.execute()
        val expected = Note(1, "test text")

        Assertions.assertEquals(expected, actual)
    }

}