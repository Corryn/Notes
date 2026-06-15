package com.corryn.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.corryn.notes.model.AppDestinations
import com.corryn.notes.ui.screens.NoteDetailsScreen
import com.corryn.notes.ui.screens.NoteListScreen
import com.corryn.notes.ui.screens.SettingsScreen
import com.corryn.notes.ui.theme.NotesTheme

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
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.NOTES) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            painterResource(it.icon),
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotesTheme {
        Greeting("Android")
    }
}