package com.corryn.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.corryn.notes.ui.screens.NoteDetailsScreen
import com.corryn.notes.ui.screens.NoteListScreen
import com.corryn.notes.ui.screens.SettingsScreen
import com.corryn.notes.ui.theme.NotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesTheme {
                NotesApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun NotesApp() {
    val appBackstack = rememberAppNavBackStack(NotesDestination.Notes)

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            BottomMenuItems.entries.forEach {
                item(
                    icon = {
                        Icon(
                            painterResource(it.icon),
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    // Assumes that we do not want to show selected if we are not at a root destination.
                    selected = it.destination == appBackstack.lastOrNull(),
                    onClick = {
                        appBackstack.clear()
                        appBackstack.add(it.destination)
                    }
                )
            }
        }
    ) {
        NavDisplay(
            backStack = appBackstack,
            onBack = {
                appBackstack.removeLastOrNull()
            },
            entryProvider = { key ->
                when (key) {
                    is NotesDestination.Notes -> NavEntry(key) {
                        NoteListScreen()
                    }

                    is NotesDestination.Details -> NavEntry(key) {
                        NoteDetailsScreen(id = key.id)
                    }

                    is NotesDestination.Settings -> NavEntry(key) {
                        SettingsScreen()
                    }
                }
            }
        )
    }
}
