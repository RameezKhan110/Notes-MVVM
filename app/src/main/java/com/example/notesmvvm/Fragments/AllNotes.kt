package com.example.notesmvvm.Fragments

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesmvvm.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AllNotes : Fragment(), AdapterClass.NotesAdapter {

    lateinit var mainViewModel : MainViewModel
    val allNotes = ArrayList<Notes>()
    var title : String = ""
    var content : String = ""
    var id : Int? = 0
    private lateinit var sharedViewModel : SharedViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        (activity as AppCompatActivity).supportActionBar?.title = "All Notes"

        val view = inflater.inflate(R.layout.fragment_all_notes, container, false)
        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerView)
        val addNotes : FloatingActionButton = view.findViewById(R.id.button)

        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = context?.let {
            AdapterClass(it, this, this)
        }
        recyclerView.adapter = adapter

        val dao = NoteDatabase.getDatabase(requireContext()).noteDao()
        val repo = NoteRepo(dao)
        mainViewModel = ViewModelProvider(this, MainVIewModelFactory(repo)).get(MainViewModel::class.java)

        mainViewModel.getNotes().observe(viewLifecycleOwner, Observer {List ->
            //checking nullability
            List?. let {
                adapter?.updateList(it)
            }

        })


        addNotes.setOnClickListener {
            findNavController().navigate(R.id.action_allNotes_to_addEdit)
        }



        return view
    }

    override fun onItemClicked(note : Notes){

        mainViewModel.deleteNote(note)
    }

    override fun onEditClicked(note: Notes) {

        title = note.title
        content = note.desc
        id = note.id

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedViewModel.id.value = id!!
        sharedViewModel.title.value = title
        sharedViewModel.content.value = content
        sharedViewModel.isEditMode.value = true

        findNavController().navigate(R.id.action_allNotes_to_addEdit)
//        val bundle = Bundle()
//        bundle.putString("title", title)
//        bundle.putString("content", content)
//        id?.let { bundle.putInt("id", it) }
//        bundle.putBoolean("isEditMode", true)

    }

//    override fun onDataPassing(note: Notes) {
//        title = note.title
//        content = note.desc
//        id = note.id
//    }

}