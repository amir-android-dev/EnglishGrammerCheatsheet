package com.amir.englishgrammercheatsheet.room
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase:RoomDatabase() {


    abstract val noteDAO:NoteDAO

    companion object{
        @Volatile
        private var INSTANCE: NoteDatabase? =null
        //it returns our database
        fun getInstance(context: Context):NoteDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance==null){
                    /*
                    Here we need to provide the applicationContext as the context
                     */
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "note_database"
                    ).build()
                }
                return instance
            }
        }
    }
}