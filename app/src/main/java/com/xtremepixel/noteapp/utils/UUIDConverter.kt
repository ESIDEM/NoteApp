package com.xtremepixel.noteapp.utils

import androidx.room.TypeConverter
import java.util.*

class UUIDConverter {

    @TypeConverter
    fun fromUUIS(uuid: UUID): String = uuid.toString()

    @TypeConverter
    fun uuidFromString(string: String) = UUID.fromString(string)
}