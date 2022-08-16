package com.amir.englishgrammercheatsheet.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class NoteViewModelFactory(private val repository: NoteRepository):ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //  return super.create(modelClass)
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}