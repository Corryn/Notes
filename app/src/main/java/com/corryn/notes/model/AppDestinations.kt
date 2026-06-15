package com.corryn.notes.model

import com.corryn.notes.R

enum class AppDestinations(
    val label: String,
    val icon: Int,
) {
    NOTES("Notes", R.drawable.ic_home),
    SETTINGS("Settings", R.drawable.ic_favorite),
}