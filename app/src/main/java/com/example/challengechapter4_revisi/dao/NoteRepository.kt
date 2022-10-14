package com.example.challange4.room

import androidx.lifecycle.LiveData
import com.example.challengechapter4_revisi.dao.NoteDao
import com.example.challengechapter4_revisi.dao.NoteData


class NoteRepository(private val data: NoteDao) {

    fun getAllDataNotes() : LiveData<List<NoteData>>{
        return data.getDataNote()
    }

    suspend fun addNote(notes: NoteData) = data.insertNote(notes)

    suspend fun editNote(notes: NoteData) = data.editNote(notes)

    suspend fun deleteNote(notes: NoteData) = data.deleteNote(notes)

}