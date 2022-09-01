package com.hydratech.roomwithmvvmandnavigationcomp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.hydratech.roomwithmvvmandnavigationcomp.R
import com.hydratech.roomwithmvvmandnavigationcomp.fragments.HomeFragmentDirections
import com.hydratech.roomwithmvvmandnavigationcomp.room.Notes
import kotlinx.android.synthetic.main.fragment_add_note.view.*
import kotlinx.android.synthetic.main.recyler_list.view.*

class NoteAdapter(val list : List<Notes>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyler_list,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.recyler_list_Title.text = list[position].title
        holder.view.recyler_list_Description.text = list[position].description
        holder.view.mRelativerecycler.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddNoteFragment()
            action.note = list[position]
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
      return  list.size
    }
}