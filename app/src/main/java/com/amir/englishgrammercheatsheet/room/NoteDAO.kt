package com.amir.englishgrammercheatsheet.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDAO {

    @Insert
    suspend fun insertNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Query("delete from note_table")
    suspend fun deleteAll()

    @Query("select * from note_table")
    fun getAllNotes(): Flow<List<NoteEntity>>


}