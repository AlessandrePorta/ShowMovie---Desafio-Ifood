package com.example.showmovie.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showmovie.usecase.GetDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val movieId: String,
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        load(movieId.toInt())
    }

    private fun load(id: Int) {
        viewModelScope.launch {
            val movie = getDetailsUseCase(id)
            if (movie == null) _uiState.emit(DetailUiState.Error)
            else _uiState.emit(DetailUiState.Success(movie))
        }
    }
}