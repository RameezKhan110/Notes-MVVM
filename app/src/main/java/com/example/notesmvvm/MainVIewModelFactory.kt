package com.example.notesmvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainVIewModelFactory(val noteRepo: NoteRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(noteRepo) as T
    }
}