package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notesapp.data.NotesDataSource
import com.example.notesapp.model.Note
import com.example.notesapp.screen.NoteScreen
import com.example.notesapp.screen.NoteViewModel
import com.example.notesapp.ui.theme.NotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
//                    val notes = remember {
//                        mutableStateListOf<Note>()
//
//                    }
                   // val noteViewModel: viewModel<NoteViewModel>()
                    //another way

                    val noteViewModel: NoteViewModel by viewModels()
                    NotesApp(noteViewModel = noteViewModel)


                   }
                }
            }
        }
    }



@Composable
fun NotesApp(noteViewModel: NoteViewModel) {
    val notesList = noteViewModel.noteList.collectAsState().value

    NoteScreen(notes = notesList,
        onAddNote = {
            noteViewModel.addNote(it)

        },
        onRemoveNote = {
            noteViewModel.removeNote(it)
        },
        onUpdateNote = {
            noteViewModel.updateNote(it)
        })
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    NotesAppTheme {
//        Greeting("Android")
//    }
//}










