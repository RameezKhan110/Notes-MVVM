package com.example.notesmvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var title = MutableLiveData<String>()
    var content = MutableLiveData<String>()
    var id  = MutableLiveData(0)
    var isEditMode = MutableLiveData(false)
}