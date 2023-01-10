package com.example.notesmvvm

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "Notes")
data class Notes(

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val desc : String
){
    constructor(id : Int) : this(id, "", "")
}
