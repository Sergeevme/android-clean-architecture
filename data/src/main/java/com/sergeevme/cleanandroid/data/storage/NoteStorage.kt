package com.sergeevme.cleanandroid.data.storage

import com.sergeevme.cleanandroid.data.storage.models.NoteData

interface NoteStorage {

    fun save(noteData: NoteData): Boolean

    fun get(): NoteData

}