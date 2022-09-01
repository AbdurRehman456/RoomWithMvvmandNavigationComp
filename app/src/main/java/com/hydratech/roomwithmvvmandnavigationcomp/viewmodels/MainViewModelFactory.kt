package com.hydratech.roomwithmvvmandnavigationcomp.viewmodels

import android.text.Editable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hydratech.roomwithmvvmandnavigationcomp.repository.NoteRepository

class MainViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}