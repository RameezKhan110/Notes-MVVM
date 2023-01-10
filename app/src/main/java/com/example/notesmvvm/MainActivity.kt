package com.example.notesmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), AdapterClass.NotesAdapter {

    lateinit var mainViewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        val addNotes : FloatingActionButton = findViewById(R.id.button)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AdapterClass(this, this)
        recyclerView.adapter = adapter

        val dao = NoteDatabase.getDatabase(this).noteDao()
        val repo = NoteRepo(dao)
        mainViewModel = ViewModelProvider(this, MainVIewModelFactory(repo)).get(MainViewModel::class.java)

        mainViewModel.getNotes().observe(this, Observer {List ->
            //checking nullability
            List?. let {
                adapter.updateList(it)
            }

        })

        val intent = intent
        val title : String? = intent.getStringExtra("title")
        val content : String?= intent.getStringExtra("content")
        val id : Int = intent.getIntExtra("id", 0)

        val deleteNotes = title?.let {
            if (content != null) {
                Notes(id, it, content)
            }
        }

        addNotes.setOnClickListener {
            val intent = Intent(this, AddEditActivity::class.java)
            startActivity(intent)
        }




    }
    override fun onItemClicked(note : Notes){
        mainViewModel.deleteNote(note)
    }
}