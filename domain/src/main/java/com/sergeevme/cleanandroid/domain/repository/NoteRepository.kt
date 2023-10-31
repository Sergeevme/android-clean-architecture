package com.sergeevme.cleanandroid.domain.repository

import com.sergeevme.cleanandroid.domain.models.Note
import com.sergeevme.cleanandroid.domain.models.SaveNote

interface NoteRepository {

    fun saveNote(saveNote: SaveNote) : Boolean

    fun getNote() : Note

}