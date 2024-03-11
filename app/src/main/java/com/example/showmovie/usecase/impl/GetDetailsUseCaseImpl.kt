package com.example.showmovie.usecase.impl

import com.example.showmovie.repository.MoviesRepository
import com.example.showmovie.ui.model.MovieVO
import com.example.showmovie.usecase.GetDetailsUseCase

internal class GetDetailsUseCaseImpl(
    private val repository: MoviesRepository
) : GetDetailsUseCase {

    override suspend fun invoke(id: Int): MovieVO? {
        return repository.getMovieDetails(id)
    }
}