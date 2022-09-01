package com.hydratech.roomwithmvvmandnavigationcomp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hydratech.roomwithmvvmandnavigationcomp.R
import com.hydratech.roomwithmvvmandnavigationcomp.repository.NoteRepository
import com.hydratech.roomwithmvvmandnavigationcomp.room.NotesDao
import com.hydratech.roomwithmvvmandnavigationcomp.room.NotesDatabase
import com.hydratech.roomwithmvvmandnavigationcomp.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment() : BaseFragment() {
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val repository =NoteRepository(note)


        addButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddNoteFragment()
            findNavController().navigate(action)
        }
    }
}