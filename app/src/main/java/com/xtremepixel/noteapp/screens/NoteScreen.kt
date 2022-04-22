package com.xtremepixel.noteapp.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xtremepixel.noteapp.R
import com.xtremepixel.noteapp.component.NoteButton
import com.xtremepixel.noteapp.component.NoteInputField
import com.xtremepixel.noteapp.data.NoteDataSource
import com.xtremepixel.noteapp.model.Note
import com.xtremepixel.noteapp.utils.formatDate
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(
    note: List<Note>,
    onAddNote: (Note) -> Unit,
    onDeleteNote: (Note) -> Unit
) {

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, backgroundColor = Color.LightGray, actions = {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Topbar Icon")
        })

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputField(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                text = title,
                label = "Title",
                maxLine = 1,
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                })

            NoteInputField(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                text = description,
                label = "Add a Note",
                maxLine = 1,
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                })

            NoteButton(text = "Save",
                onClick = {
                    if (
                        title.isNotEmpty() && description.isNotEmpty()
                    ) {
                        onAddNote(
                            Note(
                                title = title, description = description
                            )
                        )
                        title = ""
                        description = ""
                        Toast.makeText(context, "Your Note has been saved", Toast.LENGTH_LONG)
                            .show()
                    }
                })

            Divider(modifier = Modifier.padding(10.dp))

            LazyColumn {

                items(items = note) { note ->
                    NoteItem(note = note, onNoteClick = {
                        onDeleteNote(note)
                    })
                }
            }


        }
    }

}

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note, onNoteClick: (Note) -> Unit
) {

    Surface(
        modifier = modifier
            .padding(4.dp)
            .clip(
                RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp)
            )
            .fillMaxWidth(), color = Color.LightGray, elevation = 6.dp
    ) {
        Column(
            modifier
                .clickable {
                    onNoteClick(note)
                }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {
            Text(text = note.title, style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Text(
                text = formatDate(note.entryDate.time),
                style = MaterialTheme.typography.subtitle1
            )


        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    NoteScreen(note = NoteDataSource().getNotes(), onAddNote = {}, onDeleteNote = {})
}