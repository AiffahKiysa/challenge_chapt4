package com.example.challengechapter4_revisi.dao

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(notes: NoteData)

    @Query(" SELECT * FROM NoteData ORDER BY id_notes DESC")
    fun getDataNote() : LiveData<List<NoteData>>

    @Update
    suspend fun editNote(notes: NoteData)

    @Delete
    suspend fun deleteNote(notes: NoteData)
}
