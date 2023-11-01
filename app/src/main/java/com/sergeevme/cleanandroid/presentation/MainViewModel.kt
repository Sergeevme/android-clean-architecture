package com.sergeevme.cleanandroid.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergeevme.cleanandroid.domain.models.Note
import com.sergeevme.cleanandroid.domain.models.SaveNote
import com.sergeevme.cleanandroid.domain.usecase.GetNoteUseCase
import com.sergeevme.cleanandroid.domain.usecase.SaveNoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val TAG = "Logs:MainViewModel"

class MainViewModel(
    private val getNoteUseCase: GetNoteUseCase,
    private val saveNoteUseCase: SaveNoteUseCase
) : ViewModel() {

    // LiveData
    private val _loadLiveMutable = MutableLiveData<String>()
    val loadLive: LiveData<String> = _loadLiveMutable

    // StateFlow
    private val _stateFlowMutable = MutableStateFlow<Int>(0)
    val stateFlow = _stateFlowMutable.asStateFlow()

    private val lastActionLiveMutable = MutableLiveData<String>()
    val lastActionLive: LiveData<String> = lastActionLiveMutable

    init {
        Log.d(TAG, "init")
    }

    // This method will be called when this ViewModel is no longer used and will be destroyed
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared")
    }

    // Simple count updater without saving anything
    // Let's not complicate things by adding useCase and models
    fun updateCount() {
        Log.d(TAG, "updateCount")
        _stateFlowMutable.value += 1
    }

    // Saving result and update live data value
    fun save(text: String) {
        Log.d(TAG, "save")
        val saveNote = SaveNote(text = text)
        val status: Boolean = saveNoteUseCase.execute(saveNote)

        if (status) lastActionLiveMutable.value = "Saved"
        else lastActionLiveMutable.value = "Not Saved"
    }

    // Load last result update live data value
    fun load() {
        Log.d(TAG, "load")
        val note: Note = getNoteUseCase.execute()
        lastActionLiveMutable.value = "Loaded"
        _loadLiveMutable.value = note.text
    }

}