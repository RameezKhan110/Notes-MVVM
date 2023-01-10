package com.example.notesmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title : TextView = findViewById(R.id.detailtitle)
        val content : TextView = findViewById(R.id.detailnote)

        val intent = intent
        val mytitle : String? = intent.getStringExtra("title")
        val mycontent : String? = intent.getStringExtra("content")

        title.text = mytitle
        content.text = mycontent


    }
}