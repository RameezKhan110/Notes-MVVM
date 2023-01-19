//package com.example.notesmvvm
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.EditText
//import android.widget.Toast
//import androidx.lifecycle.ViewModelProvider
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//
//class AddEditActivity : AppCompatActivity() {
//
//    lateinit var mainViewModel: MainViewModel
//    lateinit var title : EditText
//    lateinit var note : EditText
//    var id : Int = 0
//    var isEditMode : Boolean = false
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_edit)
//
//        title = findViewById(R.id.title)
//        note= findViewById(R.id.note)
//        var save : FloatingActionButton = findViewById(R.id.add)
//
//        val intent = intent
//        val edittitle : String? = intent.getStringExtra("title")
//        val editnote : String? = intent.getStringExtra("content")
//        id = intent.getIntExtra("id", 0)
//        isEditMode = intent.getBooleanExtra("isEditMode", false)
//
//        if(isEditMode == true){
//            supportActionBar!!.title = "Update Notes"
//
//            title.setText(edittitle)
//            note.setText(editnote)
//
//
//        }else{
//            supportActionBar!!.title = "Add Notes"
//        }
//
//
//        val dao = NoteDatabase.getDatabase(this).noteDao()
//        val repository = NoteRepo(dao)
//        mainViewModel = ViewModelProvider(this, MainVIewModelFactory(repository)).get(MainViewModel::class.java)
//
//        save.setOnClickListener {
//
//            saveData()
//        }
//    }
//
//    private fun saveData(){
//
//        var mytitle : String  = title.text.toString()
//        var mynote : String = note.text.toString()
//
//        if(!mytitle.isEmpty() or !mynote.isEmpty()){
//            if(isEditMode == true){
//                val updatedNotes = Notes(id, mytitle, mynote)
//                mainViewModel.updateNote(updatedNotes)
//
//                Toast.makeText(this, "Notes Updated", Toast.LENGTH_SHORT).show()
//                startActivity(Intent(this, MainActivity::class.java))
//            }else{
//                val insertedNote = Notes(0, mytitle, mynote)
//                mainViewModel.insertNote(insertedNote)
//
//                Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show()
//                startActivity(Intent(this, MainActivity::class.java))
//            }
//        }else{
//            Toast.makeText(this, "Nothing to save", Toast.LENGTH_SHORT).show()
//        }
//
//
//    }
//}