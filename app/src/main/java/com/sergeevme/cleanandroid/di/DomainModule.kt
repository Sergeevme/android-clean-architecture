package com.sergeevme.cleanandroid.di

import com.sergeevme.cleanandroid.domain.usecase.GetNoteUseCase
import com.sergeevme.cleanandroid.domain.usecase.SaveNoteUseCase
import org.koin.dsl.module

/*
* The domain layer is an optional layer that sits between the UI and data layers.
*/
val domainModule = module {

    factory<GetNoteUseCase> {
        GetNoteUseCase(noteRepository = get())
    }

    factory<SaveNoteUseCase> {
        SaveNoteUseCase(noteRepository = get())
    }

}