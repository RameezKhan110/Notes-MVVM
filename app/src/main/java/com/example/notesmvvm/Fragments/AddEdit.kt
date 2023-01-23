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
import com.example.notesmvvm.databinding.FragmentAddEditBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AddEdit : Fragment() {

    private lateinit var binding : FragmentAddEditBinding
    lateinit var mainViewModel: MainViewModel
    var id : Int? = 0
    var isEditMode : Boolean? = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentAddEditBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_edit, container, false)



        val sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedViewModel.id.observe(viewLifecycleOwner, Observer{
            id = it
        })
        sharedViewModel.isEditMode.observe(viewLifecycleOwner, Observer {
            isEditMode = it
        })
        val isEditMode = sharedViewModel.isEditMode


        if(isEditMode.value == true){
            (activity as AppCompatActivity).supportActionBar?.title = "Update Notes"

            sharedViewModel.title.observe(viewLifecycleOwner, Observer {
                binding.title.setText(it)
            })

            sharedViewModel.content.observe(viewLifecycleOwner, Observer {
                binding.note.setText(it)
            })


        }else{
            (activity as AppCompatActivity).supportActionBar?.title = "Add Notes"
        }


        val dao = NoteDatabase.getDatabase(requireContext()).noteDao()
        val repository = NoteRepo(dao)
        mainViewModel = ViewModelProvider(this, MainVIewModelFactory(repository)).get(MainViewModel::class.java)

        binding.save.setOnClickListener {

            saveData()
        }

        return view
    }

    private fun saveData(){

        var mytitle : String  = binding.title.text.toString()
        var mynote : String = binding.note.text.toString()

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