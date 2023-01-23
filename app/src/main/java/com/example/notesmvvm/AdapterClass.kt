package com.example.notesmvvm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesmvvm.Fragments.AddEdit
import com.example.notesmvvm.databinding.ActivityMainBinding
import com.example.notesmvvm.databinding.FragmentAddEditBinding
import com.example.notesmvvm.databinding.FragmentAllNotesBinding
import com.example.notesmvvm.databinding.ItemSampleBinding

class AdapterClass(private val context : Context, val listener : NotesAdapter, val editlistener : NotesAdapter) : RecyclerView.Adapter<AdapterClass.viewHolder>() {

    val myNotes = ArrayList<Notes>()
    private lateinit var binding: ItemSampleBinding


    inner class viewHolder(val binding : ItemSampleBinding) : RecyclerView.ViewHolder(binding.root){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val viewholder = viewHolder(ItemSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false))


        viewholder.binding.deletebutton.setOnClickListener {
            listener.onItemClicked(myNotes[viewholder.adapterPosition])
        }
        viewholder.binding.edit.setOnClickListener{
            editlistener.onEditClicked(myNotes[viewholder.adapterPosition])
//            datapassing.onDataPassing(myNotes[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {


        val titleNote = myNotes[position]
        holder.binding.title.text =  titleNote.title
        holder.binding.content.text = titleNote.desc

        holder.binding.linearlayout.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("title", titleNote.title)
            intent.putExtra("content", titleNote.desc)
            context.startActivity(intent)
        }

//        holder.edit.setOnClickListener {
//
//
//
////            val intent = Intent(context, AddEdit::class.java)
////            intent.putExtra("title", titleNote.title)
////            intent.putExtra("content", titleNote.desc)
////            intent.putExtra("id", titleNote.id)
////            intent.putExtra("isEditMode", true)
////            context.startActivity(intent)
//        }


    }

    fun updateList(newList : List<Notes>){
        myNotes.clear()
        myNotes.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return myNotes.size
    }
    interface NotesAdapter{
        fun onItemClicked(note : Notes)
        fun onEditClicked(note : Notes)
//        fun onDataPassing(note : Notes)
    }
}