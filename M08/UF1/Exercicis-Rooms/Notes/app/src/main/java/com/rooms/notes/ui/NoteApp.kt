package com.rooms.notes.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rooms.notes.R
import com.rooms.notes.ui.screens.NotesScreen
import com.rooms.notes.ui.viewmodel.NoteViewModel

enum class NoteApp(@StringRes val title: Int) {
    Main(title = R.string.main_screen)
}

@Composable
fun NoteApp(viewModel: NoteViewModel, navController: NavHostController = rememberNavController()) {
    val uiStateNotes by viewModel.uiStateNotes.collectAsState()

    NavHost(navController, startDestination = NoteApp.Main.name) {
        composable(route = NoteApp.Main.name) {
            NotesScreen(uiStateNotes.notes, viewModel)
        }
    }
}