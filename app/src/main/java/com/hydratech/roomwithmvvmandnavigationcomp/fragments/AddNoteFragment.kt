package com.hydratech.roomwithmvvmandnavigationcomp.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.provider.ContactsContract
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.hydratech.roomwithmvvmandnavigationcomp.R
import com.hydratech.roomwithmvvmandnavigationcomp.activities.toast
import com.hydratech.roomwithmvvmandnavigationcomp.repository.NoteRepository
import com.hydratech.roomwithmvvmandnavigationcomp.room.Notes
import com.hydratech.roomwithmvvmandnavigationcomp.room.NotesDatabase
import com.hydratech.roomwithmvvmandnavigationcomp.viewmodels.MainViewModel
import com.hydratech.roomwithmvvmandnavigationcomp.viewmodels.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.launch

class AddNoteFragment : BaseFragment() {
    lateinit var mainViewModel: MainViewModel
    private var notes: Notes? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val quotesDao = NotesDatabase.getDataBase(requireContext()).notesDao()
        val repository = NoteRepository(quotesDao)
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            notes = AddNoteFragmentArgs.fromBundle(it).note
            titleTextView.setText(notes?.title)
            descreptionTextView.setText(notes?.description)
        }
        saveButton.setOnClickListener {
            val noteTitle = titleTextView.text.toString().trim()
            val noteDesc = descreptionTextView.text.toString().trim()
            if (noteTitle.isEmpty()) {
                titleTextView.error = "title required"
                titleTextView.requestFocus()
                return@setOnClickListener
            }
            if (noteDesc.isEmpty()) {
                descreptionTextView.error = "Note required"
                descreptionTextView.requestFocus()
                return@setOnClickListener
            }
            launch {
                val mnote = Notes(0, noteTitle, noteDesc)
                context?.let {
//                    NotesDatabase.getDataBase(it).notesDao().insertNotes(note)
                    if (notes == null){
                        mainViewModel.insertNote(mnote)
                        it.toast("Note Saved")
                    }else{
                        mnote.id = notes!!.id
                        mainViewModel.updateNote(mnote)
                        it.toast("Note updated")

                    }

                    val action = AddNoteFragmentDirections.actionAddNoteFragmentToHomeFragment()
                    findNavController().navigate(action)

                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteItem -> if (notes != null ) deleteNote() else context?.toast("canNot Delete")
        }
        return super.onOptionsItemSelected(item)

    }

    private fun deleteNote() {
        AlertDialog.Builder(context).apply {
            setTitle("Are you sure?")
            setMessage("can not undo this")
            setPositiveButton("Yes"){_,_ ->
                launch {
                    mainViewModel.deleteNote(notes!!)
                    val action = AddNoteFragmentDirections.actionAddNoteFragmentToHomeFragment()
                    findNavController().navigate(action)
                }
            }
            setNegativeButton("No"){_,_ ->

            }
        }.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu,menu)
    }
}