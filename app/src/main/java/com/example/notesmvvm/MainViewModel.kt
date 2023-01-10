package com.example.notesmvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(val noteRepo: NoteRepo) : ViewModel() {

    fun getNotes() : LiveData<List<Notes>>{
        return noteRepo.getNotes()
    }

    fun insertNote(notes : Notes){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepo.insertNote(notes)
        }
    }

    fun updateNote(notes : Notes){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepo.updateNote(notes)
        }
    }

    fun deleteNote(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepo.deleteNote(notes)
        }
    }
}