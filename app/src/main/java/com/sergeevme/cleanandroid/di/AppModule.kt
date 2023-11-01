package com.sergeevme.cleanandroid.di

import com.sergeevme.cleanandroid.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/*
* The role of the UI layer (or presentation layer) is to display the application data on the screen.
* Whenever the data changes, either due to user interaction (such as pressing a button)
* or external input (such as a network response), the UI should update to reflect the changes.
*/
val appModule = module {

    viewModel<MainViewModel>() {
        MainViewModel(getNoteUseCase = get(), saveNoteUseCase = get())
    }

}