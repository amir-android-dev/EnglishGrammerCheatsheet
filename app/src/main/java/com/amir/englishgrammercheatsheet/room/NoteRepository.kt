package com.amir.englishgrammercheatsheet.room

class NoteRepository(private val dao: NoteDAO) {

    val notes = dao.getAllNotes()

    suspend fun insert(noteEntity: NoteEntity): Long {
        return dao.insertNote(noteEntity)
    }

    suspend fun update(noteEntity: NoteEntity): Int {
        return dao.updateNote(noteEntity)
    }

    suspend fun delete(noteEntity: NoteEntity): Int {
        return dao.deleteNote(noteEntity)
    }

    suspend fun deleteAll(): Int {
        return dao.deleteAll()
    }
}