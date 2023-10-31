package com.sergeevme.cleanandroid.domain.usecase

import com.sergeevme.cleanandroid.domain.models.SaveNote
import com.sergeevme.cleanandroid.domain.repository.NoteRepository

class SaveNoteUseCase(private val noteRepository: NoteRepository) {

    fun execute(saveNote: SaveNote) : Boolean {

        if (saveNote.text.trim().isEmpty()) return false

        val oldNote = noteRepository.getNote()
        if (oldNote.text == saveNote.text) return true

        return noteRepository.saveNote(saveNote = saveNote)
    }

}