package com.sergeevme.cleanandroid.di

import com.sergeevme.cleanandroid.domain.usecase.GetNoteUseCase
import com.sergeevme.cleanandroid.domain.usecase.SaveNoteUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetNoteUseCase> {
        GetNoteUseCase(noteRepository = get())
    }

    factory<SaveNoteUseCase> {
        SaveNoteUseCase(noteRepository = get())
    }

}