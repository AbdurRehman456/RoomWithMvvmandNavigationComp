package com.hydratech.roomwithmvvmandnavigationcomp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hydratech.roomwithmvvmandnavigationcomp.R
import com.hydratech.roomwithmvvmandnavigationcomp.adapters.NoteAdapter
import com.hydratech.roomwithmvvmandnavigationcomp.repository.NoteRepository
import com.hydratech.roomwithmvvmandnavigationcomp.room.NotesDao
import com.hydratech.roomwithmvvmandnavigationcomp.room.NotesDatabase
import com.hydratech.roomwithmvvmandnavigationcomp.viewmodels.MainViewModel
import com.hydratech.roomwithmvvmandnavigationcomp.viewmodels.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch

class HomeFragment() : BaseFragment() {
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val quotesDao = NotesDatabase.getDataBase(requireContext()).notesDao()
        val repository = NoteRepository(quotesDao)
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val repository =NoteRepository(note)

        recylerView.setHasFixedSize(true)
        recylerView.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        mainViewModel.getNotes().observe(viewLifecycleOwner, Observer {
            recylerView.adapter = NoteAdapter(it)
        })




        addButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddNoteFragment()
            findNavController().navigate(action)
        }
    }
}