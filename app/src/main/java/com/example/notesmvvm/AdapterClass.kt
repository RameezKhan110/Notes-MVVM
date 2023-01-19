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
import androidx.recyclerview.widget.RecyclerView
import com.example.notesmvvm.Fragments.AddEdit

class AdapterClass(private val context : Context, val listener : NotesAdapter, val editlistener : NotesAdapter) : RecyclerView.Adapter<AdapterClass.viewHolder>() {

    val myNotes = ArrayList<Notes>()


    inner class viewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.title)
        val content : TextView = itemView.findViewById(R.id.note)
        val deleteButton : ImageView = itemView.findViewById(R.id.deletebutton)
        val linearLayout : LinearLayout = itemView.findViewById(R.id.linearlayout)
        val edit : ImageView = itemView.findViewById(R.id.edit)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val viewholder = viewHolder(LayoutInflater.from(context).inflate(R.layout.item_sample,parent, false))
        viewholder.deleteButton.setOnClickListener {
            listener.onItemClicked(myNotes[viewholder.adapterPosition])
        }
        viewholder.edit.setOnClickListener{
            editlistener.onEditClicked(myNotes[viewholder.adapterPosition])
//            datapassing.onDataPassing(myNotes[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {


        val titleNote = myNotes[position]
        holder.title.text =  titleNote.title
        holder.content.text = titleNote.desc

        holder.linearLayout.setOnClickListener {
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