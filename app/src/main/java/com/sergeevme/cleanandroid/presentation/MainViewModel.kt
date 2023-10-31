package com.sergeevme.cleanandroid.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergeevme.cleanandroid.domain.models.Note
import com.sergeevme.cleanandroid.domain.models.SaveNote
import com.sergeevme.cleanandroid.domain.usecase.GetNoteUseCase
import com.sergeevme.cleanandroid.domain.usecase.SaveNoteUseCase

private const val TAG = "Logs:MainViewModel"

class MainViewModel(
    private val getNoteUseCase: GetNoteUseCase,
    private val saveNoteUseCase: SaveNoteUseCase
) : ViewModel() {

    private val loadLiveMutable = MutableLiveData<String>()
    val loadLive: LiveData<String> = loadLiveMutable

    private val lastActionLiveMutable = MutableLiveData<String>()
    val lastActionLive: LiveData<String> = lastActionLiveMutable

    init {
        Log.d(TAG, "init")
    }



    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared")
    }

    fun save(text: String) {
        val saveNote = SaveNote(text = text)
        val status: Boolean = saveNoteUseCase.execute(saveNote)

        if (status) lastActionLiveMutable.value = "Saved"
        else lastActionLiveMutable.value = "Not Saved"
    }

    fun load() {
        val note: Note = getNoteUseCase.execute()
        lastActionLiveMutable.value = "Loaded"
        loadLiveMutable.value = note.text
    }

}