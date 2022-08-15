package com.amir.englishgrammercheatsheet.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDAO {

    @Insert
    suspend fun insertNote(note: NoteEntity):Long

    @Update
    suspend fun updateNote(note: NoteEntity):Int

    @Delete
    suspend fun deleteNote(note: NoteEntity):Int

    @Query("delete from note_table")
    suspend fun deleteAll():Int

    @Query("select * from note_table")
    fun getAllNotes(): Flow<List<NoteEntity>>
}