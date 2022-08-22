package com.amir.englishgrammercheatsheet.room

class NoteRepository(private val dao: NoteDAO) {

    val notes = dao.getAllNotes()

    suspend fun insert(noteEntity: NoteEntity) {
        return dao.insertNote(noteEntity)
    }

    suspend fun update(noteEntity: NoteEntity) {
        return dao.updateNote(noteEntity)
    }

    suspend fun delete(noteEntity: NoteEntity) {
        return dao.deleteNote(noteEntity)
    }

    suspend fun deleteAll() {
        return dao.deleteAll()
    }
}