package com.corryn.notes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.corryn.notes.model.NoteModel
import com.corryn.notes.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val notesRepository: NotesRepository
): ViewModel() {

    init {
        viewModelScope.launch {
            val notes = notesRepository.getNotes()

            _uiState.value = NoteListUiState.Ready(
                notes = notes
            )
        }
    }

    private val _uiState = MutableStateFlow<NoteListUiState>(NoteListUiState.Loading)
    val uiState = _uiState.asStateFlow()

}

sealed interface NoteListUiState {

    data object Loading: NoteListUiState
    data object Error: NoteListUiState
    data class Ready(
        val notes: List<NoteModel>
    ): NoteListUiState

}