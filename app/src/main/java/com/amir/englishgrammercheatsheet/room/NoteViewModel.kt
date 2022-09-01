package com.amir.englishgrammercheatsheet.room

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.amir.englishgrammercheatsheet.Event
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    private val statusMessage = MutableLiveData<Event<String>>()

    val inputTitle = MutableLiveData<String>()
    val inputDescription = MutableLiveData<String>()

    private lateinit var noteEntityToUpdate: NoteEntity


    fun saveOrUpdate() {
        val title = inputTitle.value!!
        val description = inputDescription.value!!
        insert(NoteEntity(0, title = title, description = description))
        inputTitle.value = ""
        inputDescription.value = ""

    }


    fun insert(noteEntity: NoteEntity): Job = viewModelScope.launch {
//        val newRowId: Long = repository.insert(noteEntity)
//        if (newRowId > -1) {
//            statusMessage.value = Event("Subscriber inserted successfully $newRowId")
//        } else {
//            statusMessage.value = Event("Error Occurred")
//        }
       // repository.insert(NoteEntity(title = inputTitle.value.toString(), description = inputDescription.value.toString()))
        repository.insert(noteEntity)
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun update(noteEntity: NoteEntity): Job = viewModelScope.launch {
        repository.update(noteEntity)
    }

    @SuppressLint("NullSafeMutableLiveData")
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