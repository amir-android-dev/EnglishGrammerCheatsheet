package com.amir.englishgrammercheatsheet.room

import android.annotation.SuppressLint
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
    private var isUpdated = false
    private lateinit var noteEntityToUpdate: NoteEntity

    val saveOrUpdateButtonText = MutableLiveData<String>()
    val cancelButton = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        cancelButton.value = "Cancel"
    }

    fun saveOrUpdate() {
        if (isUpdated){
            noteEntityToUpdate.title = inputTitle.value!!
            noteEntityToUpdate.description = inputDescription.value!!
            update(noteEntityToUpdate)
        }else{
            val title = inputTitle.value!!
            val description = inputDescription.value!!
            insert(NoteEntity(0, title, description))
            //inputTitle.value = ""
            // inputDescription.value = ""
        }
    }
    fun initUpdate(noteEntity: NoteEntity){
        inputTitle.value = noteEntity.title
        inputDescription.value = noteEntity.description
        isUpdated = true
        noteEntityToUpdate = noteEntity
        saveOrUpdateButtonText.value = "Update"
    }

    fun insert(noteEntity: NoteEntity): Job = viewModelScope.launch {
        repository.insert(noteEntity)
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun update(noteEntity: NoteEntity): Job = viewModelScope.launch {
        repository.update(noteEntity)
        inputTitle.value = null
        inputDescription.value = null
        isUpdated = false
        noteEntityToUpdate = noteEntity

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