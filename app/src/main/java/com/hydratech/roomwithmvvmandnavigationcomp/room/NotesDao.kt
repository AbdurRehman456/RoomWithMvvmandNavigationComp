package com.hydratech.roomwithmvvmandnavigationcomp.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    @Query("Select * from notes order by id Desc ")
      fun getNotes() : LiveData<List<Notes>>

    @Insert
    suspend fun insertNotes(notes: Notes)

    @Update
    suspend fun updateNote(notes: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)

}