package com.example.notesapp.screen

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.notesapp.components.NoteButton
import com.example.notesapp.components.NoteInputText
import com.example.notesapp.model.Note
import com.example.notesapp.util.formatDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit,
    onUpdateNote: (Note) -> Unit,
) {

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var context = LocalContext.current

    var isEditing by remember {
        mutableStateOf(false)
    }

    var noteBeingEdited by remember {
        mutableStateOf<Note?>(null)
    }
    
    Column() {
        
        TopAppBar(title = { Text(text = "NotesApp")},
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icon")
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Magenta,
                titleContentColor = Color.Black))

        // Content

        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            NoteInputText(
                modifier = Modifier.padding(top = 9.dp,
                    bottom = 8.dp),
                text = title,
                label = "Title",
                onTextChange = {
                    if(it.all { char ->
                    char.isLetterOrDigit() || char.isWhitespace()

                }) title = it
                })
             NoteInputText(
                modifier = Modifier.padding(top = 9.dp,
                    bottom = 8.dp),
                text = description,
                label = "Add a note",
                onTextChange = {
                    if(it.all { char ->
                    char.isLetterOrDigit() || char.isWhitespace()

                }) description = it
                })

            NoteButton(text = if(isEditing) "Update" else "Save",
                onClick = {
                    if(title.isNotEmpty() && description.isNotEmpty()) {
                        val newNote = noteBeingEdited?.copy(title = title, description = description)
                            ?: Note(title = title, description = description)

                        if(isEditing)
                        {
                            onUpdateNote(newNote)
                            Toast.makeText(context, "Note Updated", Toast.LENGTH_SHORT).show()
                        }
                        else
                        {
                            onAddNote(newNote)
                            Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                        }


//                        onAddNote(Note(title = title,
//                            description = description))

                        title = ""
                        description = ""
                        isEditing = false
                        noteBeingEdited = null

//                        Toast.makeText(context, "Note Added",
//                            Toast.LENGTH_SHORT).show()
                    }
                })
        }

        HorizontalDivider(modifier = Modifier.padding(10.dp))

        LazyColumn {
            items(notes) { note ->
                //Text(text = note.title)
                
                NoteRow(note = note,
                    onNoteClicked = {
                        onRemoveNote(note)
                    },
                    onNoteLongClicked = {
                        title = it.title
                        description = it.description
                        isEditing = true
                        noteBeingEdited = it
                    })
            }
        }
    }
}



// Multiple things we can use
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit,
    onNoteLongClicked: (Note) -> Unit
) {
     Surface(
         modifier
             .padding(4.dp)
             .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
             .fillMaxWidth(),
         color = Color(0xFFDFE6EB),
         shadowElevation = 6.dp) {

         Column(
             modifier
//                 .clickable {
//                     onNoteClicked(note)
//v
//                 }
                 .combinedClickable(
                     onClick = {onNoteClicked(note)},
                     onLongClick = {onNoteLongClicked(note)}
                 )
                 .padding(horizontal = 14.dp, vertical = 6.dp),
             horizontalAlignment = Alignment.Start){
             
             Text(text = note.title)
             Text(text = note.description)
             Text(text = formatDate(note.entryDate.time))
         }
     }
}



