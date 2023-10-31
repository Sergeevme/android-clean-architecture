package com.sergeevme.cleanandroid.data.repository

import com.sergeevme.cleanandroid.data.storage.NoteStorage
import com.sergeevme.cleanandroid.data.storage.models.NoteData
import com.sergeevme.cleanandroid.domain.models.Note
import com.sergeevme.cleanandroid.domain.models.SaveNote
import com.sergeevme.cleanandroid.domain.repository.NoteRepository

class NoteRepositoryImpl(private val noteStorage: NoteStorage) : NoteRepository {

    override fun saveNote(saveNote: SaveNote) : Boolean {
        val noteData = mapToStorage(saveNote)
        return noteStorage.save(noteData)
    }

    override fun getNote(): Note {
        val noteData = noteStorage.get()
        return Note(id = noteData.id, text = noteData.text)
    }

    private fun mapToStorage(note: SaveNote): NoteData {
        return NoteData(1L, note.text)
    }

    private fun mapToDomain(noteData: NoteData) : Note {
        return Note(id = noteData.id, text = noteData.text);
    }

}