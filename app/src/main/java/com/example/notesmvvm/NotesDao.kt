package com.example.notesmvvm

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes")
    fun getNotes() : LiveData<List<Notes>>

    @Insert
    suspend fun insertNote(notes: Notes)

    @Delete
    suspend fun deletenote(notes: Notes)

    @Update
    suspend fun updateNote(notes: Notes)
}