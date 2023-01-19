package com.example.notesmvvm.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notesmvvm.*
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AddEdit : Fragment() {

    lateinit var mainViewModel: MainViewModel
     lateinit var title : EditText
     lateinit var note : EditText
    var id : Int? = 0
    var isEditMode : Boolean? = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_edit, container, false)

        title = view.findViewById(R.id.title)
        note= view.findViewById(R.id.note)
        var save : FloatingActionButton = view.findViewById(R.id.add)

        val sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedViewModel.id.observe(viewLifecycleOwner, Observer{
            id = it
        })
        sharedViewModel.isEditMode.observe(viewLifecycleOwner, Observer {
            isEditMode = it
        })
        val isEditMode = sharedViewModel.isEditMode

//        val edittitle : String? = arguments?.getString("title")
//        val editnote : String? = arguments?.getString("content")
//        id = arguments?.getInt("id", 0)
//        isEditMode = arguments?.getBoolean("isEditMode", false)

        if(isEditMode.value == true){
            (activity as AppCompatActivity).supportActionBar?.title = "Update Notes"

            sharedViewModel.title.observe(viewLifecycleOwner, Observer {
                title.setText(it)
            })

            sharedViewModel.content.observe(viewLifecycleOwner, Observer {
                note.setText(it)
            })


        }else{
            (activity as AppCompatActivity).supportActionBar?.title = "Add Notes"
        }


        val dao = NoteDatabase.getDatabase(requireContext()).noteDao()
        val repository = NoteRepo(dao)
        mainViewModel = ViewModelProvider(this, MainVIewModelFactory(repository)).get(MainViewModel::class.java)

        save.setOnClickListener {

            saveData()
        }

        return view
    }

    private fun saveData(){

        var mytitle : String  = title.text.toString()
        var mynote : String = note.text.toString()

        if(!mytitle.isEmpty() or !mynote.isEmpty()){
            if(isEditMode == true){
                val updatedNotes = id?.let {
                    Notes(it, mytitle, mynote)
                }
                if (updatedNotes != null) {
                    mainViewModel.updateNote(updatedNotes)
                }

                Toast.makeText(context, "Notes Updated", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addEdit_to_allNotes)
            }else{
                val insertedNote = Notes(0, mytitle, mynote)
                mainViewModel.insertNote(insertedNote)

                Toast.makeText(context, "Note Saved", Toast.LENGTH_SHORT).show()

                findNavController().navigate(R.id.action_addEdit_to_allNotes)
            }
        }else{
            Toast.makeText(context, "Nothing to save", Toast.LENGTH_SHORT).show()
        }


    }
}