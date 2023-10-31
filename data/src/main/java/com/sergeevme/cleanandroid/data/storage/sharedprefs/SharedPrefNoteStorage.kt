package com.sergeevme.cleanandroid.data.storage.sharedprefs

import android.content.Context
import com.sergeevme.cleanandroid.data.storage.NoteStorage
import com.sergeevme.cleanandroid.data.storage.models.NoteData

private const val SHARED_PREFS_NOTE_NAME =  "sharedNote"
private const val KEY_NOTE =  "note"

private const val DEFAULT_ID = 1L

class SharedPrefNoteStorage(context: Context) : NoteStorage {

    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFS_NOTE_NAME,
        Context.MODE_PRIVATE
    )

    override fun save(noteData: NoteData) : Boolean {
        sharedPreferences.edit().putString(KEY_NOTE, noteData.text).apply()
        return true
    }

    override fun get() : NoteData {
        val noteText = sharedPreferences.getString(KEY_NOTE, "" ) ?: ""
        return NoteData(DEFAULT_ID, noteText)
    }

}