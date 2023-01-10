package com.example.notesmvvm


import androidx.lifecycle.LiveData

class NoteRepo(val notesDao: NotesDao) {

    suspend fun insertNote(notes : Notes){
        notesDao.insertNote(notes)
    }

    suspend fun updateNote(notes: Notes){
        notesDao.updateNote(notes)
    }

    suspend fun deleteNote(notes: Notes){
        notesDao.deletenote(notes)

    }

    fun getNotes() : LiveData<List<Notes>>{
        return notesDao.getNotes()
    }
}