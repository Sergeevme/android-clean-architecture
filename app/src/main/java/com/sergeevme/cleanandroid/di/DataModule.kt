package com.sergeevme.cleanandroid.di

import com.sergeevme.cleanandroid.data.repository.NoteRepositoryImpl
import com.sergeevme.cleanandroid.data.storage.NoteStorage
import com.sergeevme.cleanandroid.data.storage.sharedprefs.SharedPrefNoteStorage
import com.sergeevme.cleanandroid.domain.repository.NoteRepository
import org.koin.dsl.module

/*
* The data layer of an app contains the business logic.
* The business logic is what gives value to your app—it's made of rules
* that determine how your app creates, stores, and changes data.
*/
val dataModule = module {

    single<NoteStorage> {
        SharedPrefNoteStorage(context = get())
    }

    single<NoteRepository> {
        NoteRepositoryImpl(noteStorage = get())
    }

}
