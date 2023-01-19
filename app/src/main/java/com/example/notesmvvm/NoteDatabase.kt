package com.example.notesmvvm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesmvvm.Fragments.AllNotes


@Database(entities = [Notes :: class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao() : NotesDao

    companion object{

        private var INSTANCE : NoteDatabase? = null
        fun getDatabase(context: Context) : NoteDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context, NoteDatabase :: class.java, "mynotes").build()
                }
            }
            return INSTANCE!!
        }


    }

}