package com.sergeevme.cleanandroid.di

import com.sergeevme.cleanandroid.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainViewModel>() {
        MainViewModel(getNoteUseCase = get(), saveNoteUseCase = get())
    }

}