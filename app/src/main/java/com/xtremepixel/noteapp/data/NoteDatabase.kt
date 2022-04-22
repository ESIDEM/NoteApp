package com.xtremepixel.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.xtremepixel.noteapp.model.Note
import com.xtremepixel.noteapp.utils.DateConverter
import com.xtremepixel.noteapp.utils.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDatabaseDAO
}