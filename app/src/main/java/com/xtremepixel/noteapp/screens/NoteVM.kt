package com.xtremepixel.noteapp.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xtremepixel.noteapp.data.NoteDataSource
import com.xtremepixel.noteapp.model.Note
import com.xtremepixel.noteapp.repo.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteVM @Inject constructor( private val noteRepository: NoteRepository): ViewModel() {

   // private var noteList = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.getAllNotes().distinctUntilChanged().collectLatest {
                _noteList.value = it
            }
        }
    }

     fun addNote(note: Note) = viewModelScope.launch { noteRepository.addNote(note) }
     fun updateNote(note: Note) = viewModelScope.launch { noteRepository.updateNote(note) }
     fun deleteNote(note: Note) = viewModelScope.launch { noteRepository.deleteNote(note) }
     fun deleteAllNote() = viewModelScope.launch { noteRepository.deleteAllNote() }
   // suspend fun getNoteById(id: String) = viewModelScope.launch {  }
}