package com.sergeevme.cleanandroid.di

import com.sergeevme.cleanandroid.data.repository.NoteRepositoryImpl
import com.sergeevme.cleanandroid.data.storage.NoteStorage
import com.sergeevme.cleanandroid.data.storage.sharedprefs.SharedPrefNoteStorage
import com.sergeevme.cleanandroid.domain.repository.NoteRepository
import org.koin.dsl.module

val dataModule = module {

    single<NoteStorage> {
        SharedPrefNoteStorage(context = get())
    }

    single<NoteRepository> {
        NoteRepositoryImpl(noteStorage = get())
    }

}
