package com.example.challengechapter4_revisi.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.challengechapter4_revisi.data.dao.NoteRepository
import com.example.challengechapter4_revisi.data.dao.NoteData
import com.example.challengechapter4_revisi.data.dao.NoteDatabase
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepo : NoteRepository

    init {
        val notesDao = NoteDatabase.getInstance(application)?.noteDao()
        noteRepo = NoteRepository(notesDao!!)
    }

    fun getDataNotes() : LiveData<List<NoteData>> = noteRepo.getAllDataNotes()

    fun addNote(notes: NoteData){
        viewModelScope.launch {
            noteRepo.addNote(notes)
        }
    }

    fun editNote(notes: NoteData){
        viewModelScope.launch {
            noteRepo.editNote(notes)
        }
    }

    fun deleteNote(notes: NoteData){
        viewModelScope.launch {
            noteRepo.deleteNote(notes)
        }
    }
}