package com.example.showmovie.usecase.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.showmovie.repository.MoviesRepository
import com.example.showmovie.ui.model.MovieVO
import com.example.showmovie.usecase.GetMoviesPagingUseCase
import kotlinx.coroutines.flow.Flow

internal class GetMoviesPagingUseCaseImpl(private val repository: MoviesRepository) :
    GetMoviesPagingUseCase {

    override fun invoke(
        query: String,
        pagingConfig: PagingConfig
    ): Flow<PagingData<MovieVO>> {
        return if (query.isNotEmpty()) {
            Pager(config = pagingConfig) {
                repository.searchMovies(query)
            }.flow
        } else {
            Pager(config = pagingConfig) {
                repository.getMovies()
            }.flow
        }
    }
}