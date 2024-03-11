package com.example.showmovie.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.showmovie.usecase.GetMoviesPagingUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class MoviesViewModel(private val getMoviesPagingUseCase: GetMoviesPagingUseCase) : ViewModel() {

    private val _getMovies = MutableStateFlow("")

    val pagingDataFlow = _getMovies.debounce(300)
        .flatMapLatest { query ->
            getMoviesPagingUseCase(
                query,
                pagingConfig = PagingConfig(1)
            )
        }.cachedIn(viewModelScope)

    fun search(name: String?) {
        viewModelScope.launch {
            _getMovies.update {
                name.orEmpty()
            }
        }
    }

}