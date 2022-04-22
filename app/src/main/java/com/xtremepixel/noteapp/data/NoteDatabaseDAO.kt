package com.xtremepixel.noteapp.data

import androidx.room.*
import com.xtremepixel.noteapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDAO {

    @Query(value = "SELECT * from notes" )
    fun getNotes(): Flow<List<Note>>

    @Query(value = "SELECT * from notes where id =:id ")
   suspend fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(note: Note)

    @Query(value = "DELETE from notes")
    suspend fun deleteAll()

    @Delete
   suspend fun deleteNote(note: Note)
}