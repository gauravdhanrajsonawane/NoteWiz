package com.example.notesapp.data

import com.example.notesapp.model.Note

class NotesDataSource {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "A good day", description = "We went on vacation"),
            Note(title = "A nice day", description = "We Play"),
            Note(title = "A warm day", description = "Radha Radha")

        )
    }
}


