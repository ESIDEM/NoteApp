package com.xtremepixel.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xtremepixel.noteapp.model.Note
import com.xtremepixel.noteapp.screens.NoteScreen
import com.xtremepixel.noteapp.screens.NoteVM
import com.xtremepixel.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val noteVM: NoteVM by viewModels()
                    NoteApp(noteVM = noteVM)
                }
            }
        }
    }
}

@Composable
fun NoteApp(noteVM: NoteVM){

    val noteList = noteVM.noteList.collectAsState().value
    NoteScreen(note = noteList,
        onDeleteNote = {
            noteVM.deleteNote(it)
        },
        onAddNote = {
            noteVM.addNote(it)
        })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteAppTheme {
    }
}