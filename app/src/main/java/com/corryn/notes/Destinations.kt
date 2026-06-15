package com.corryn.notes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

@Serializable
sealed interface NotesDestination: NavKey {

    @Serializable
    data object Notes: NotesDestination

    @Serializable
    data class Details(val id: String): NotesDestination

    @Serializable
    data object Settings: NotesDestination

}

/**
 * Represents the top-level menu items of the app.
 */
enum class BottomMenuItems(
    val label: String,
    val icon: Int,
    val destination: NotesDestination,
) {
    NOTES(label = "Notes", icon = R.drawable.ic_home, destination = NotesDestination.Notes),
    SETTINGS(label = "Settings", icon = R.drawable.ic_favorite, destination = NotesDestination.Settings),
}

/**
 * Custom nav back stack for supporting NotesDestination specifically, which is a subtype of NavKey.
 */
@Composable
fun rememberAppNavBackStack(vararg elements: NotesDestination): NavBackStack<NotesDestination> {
    return rememberSerializable(serializer = serializer()) {
        NavBackStack(*elements)
    }
}