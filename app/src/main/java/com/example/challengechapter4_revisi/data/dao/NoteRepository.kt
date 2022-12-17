package com.example.challengechapter4_revisi.data.dao

import androidx.lifecycle.LiveData
import com.example.challengechapter4_revisi.data.dao.NoteDao
import com.example.challengechapter4_revisi.data.dao.NoteData


class NoteRepository(private val data: NoteDao) {

    fun getAllDataNotes() : LiveData<List<NoteData>>{
        return data.getDataNote()
    }

    suspend fun addNote(notes: NoteData) = data.insertNote(notes)

    suspend fun editNote(notes: NoteData) = data.editNote(notes)

    suspend fun deleteNote(notes: NoteData) = data.deleteNote(notes)

}