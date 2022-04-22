package com.xtremepixel.noteapp.repo

import com.xtremepixel.noteapp.data.NoteDatabaseDAO
import com.xtremepixel.noteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDAO: NoteDatabaseDAO) {

    suspend fun addNote(note: Note) = noteDatabaseDAO.insert(note = note)
    suspend fun deleteNote(note: Note) = noteDatabaseDAO.deleteNote(note = note)
    fun getAllNotes(): Flow<List<Note>> = noteDatabaseDAO.getNotes().flowOn(Dispatchers.IO).conflate()
    suspend fun deleteAllNote() = noteDatabaseDAO.deleteAll()
    suspend fun getNoteById(id:String): Note = noteDatabaseDAO.getNoteById(id)
    suspend fun updateNote(note: Note) = noteDatabaseDAO.update(note)
}