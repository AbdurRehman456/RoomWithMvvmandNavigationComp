package com.hydratech.roomwithmvvmandnavigationcomp.viewmodels

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hydratech.roomwithmvvmandnavigationcomp.repository.NoteRepository
import com.hydratech.roomwithmvvmandnavigationcomp.room.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NoteRepository) : ViewModel() {

    fun getNotes(): LiveData<List<Notes>> {

        return repository.getNotes()
    }

    fun insertNote(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(notes)
        }

    }
    fun updateNote(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {repository.updateNote(notes)  }
    }

    fun deleteNote(notes: Notes){
        viewModelScope.launch(Dispatchers.IO) {repository.deleteNote(notes)  }
    }
}