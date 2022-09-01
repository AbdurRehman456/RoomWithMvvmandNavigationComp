package com.hydratech.roomwithmvvmandnavigationcomp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotesDao {
    @Query("Select * from notes")
    fun getNotes() : LiveData<List<Notes>>

    @Insert
    suspend fun insertNotes(notes: Notes)


}