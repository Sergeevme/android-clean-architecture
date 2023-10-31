package com.sergeevme.cleanandroid.domain.usecase

import com.sergeevme.cleanandroid.domain.models.Note
import com.sergeevme.cleanandroid.domain.repository.NoteRepository

class GetNoteUseCase(private val noteRepository: NoteRepository) {

    fun execute() : Note {
        return noteRepository.getNote()
    }

}