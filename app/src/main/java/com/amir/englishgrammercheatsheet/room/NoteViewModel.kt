package com.amir.englishgrammercheatsheet.room

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {


    val inputTitle = MutableLiveData<String>()
    val inputDescription = MutableLiveData<String>()

    val saveOrUpdateButtonText = MutableLiveData<String>()
    val cancelButton = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        cancelButton.value = "Cancel"
    }

    fun saveOrUpdate() {
        val title = inputTitle.value!!
        val description = inputDescription.value!!
        insert(NoteEntity(0, title, description))
        //inputTitle.value = ""
       // inputDescription.value = ""
    }

    fun insert(noteEntity: NoteEntity): Job = viewModelScope.launch {
        repository.insert(noteEntity)
    }

    fun update(noteEntity: NoteEntity): Job = viewModelScope.launch {
        repository.update(noteEntity)
    }

    fun delete(noteEntity: NoteEntity): Job = viewModelScope.launch {
        repository.delete(noteEntity)
    }

    fun clearAll(): Job = viewModelScope.launch {
        repository.deleteAll()
    }

    fun getSavedNotes() = liveData {
        repository.notes.collect {
            emit(it)
        }
    }

}