package com.example.showmovie.ui.details

import com.example.showmovie.ui.model.MovieVO

sealed interface DetailUiState {
    data object Loading : DetailUiState
    data class Success(val movie: MovieVO) : DetailUiState
    data object Error : DetailUiState
}