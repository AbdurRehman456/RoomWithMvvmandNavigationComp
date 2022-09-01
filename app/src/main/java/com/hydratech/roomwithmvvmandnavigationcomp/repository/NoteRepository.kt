package com.hydratech.roomwithmvvmandnavigationcomp.repository

import androidx.lifecycle.LiveData
import com.hydratech.roomwithmvvmandnavigationcomp.room.Notes
import com.hydratech.roomwithmvvmandnavigationcomp.room.NotesDao

class NoteRepository(private val notesDao: NotesDao) {

     fun getNotes(): LiveData<List<Notes>> {
        return notesDao.getNotes()
    }

    suspend fun insertNote(notes: Notes) {
        return notesDao.insertNotes(notes)
    }

    suspend fun updateNote(notes: Notes){
        return notesDao.updateNote(notes)
    }

    suspend fun deleteNote(notes: Notes){
        return notesDao.deleteNote(notes)
    }
}