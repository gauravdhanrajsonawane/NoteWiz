package com.example.notesapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.notesapp.model.Note
import com.example.notesapp.util.DateConverter
import com.example.notesapp.util.UUIDConverter

@Database(entities = [Note::class], version = 2, exportSchema = false)

@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDatabaseDao  // override by room library


}

