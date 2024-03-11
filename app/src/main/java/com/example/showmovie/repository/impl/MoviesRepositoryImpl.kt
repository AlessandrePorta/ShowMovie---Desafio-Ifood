package com.example.showmovie.repository.impl

import com.example.showmovie.repository.MoviesRepository
import com.example.showmovie.services.MoviesService
import com.example.showmovie.source.MoviesPagingSource
import com.example.showmovie.utils.toModel

class MoviesRepositoryImpl(private val moviesService: MoviesService) : MoviesRepository {

    override fun getMovies() = MoviesPagingSource(
        fetchMovies = {
            moviesService.getMovies(it)
        }
    )

    override fun searchMovies(query: String?) = MoviesPagingSource(
        fetchMovies = {
            moviesService.searchMovies(query, it)
        }
    )

    override suspend fun getMovieDetails(id: Int) = moviesService.getMovieDetails(id).toModel()

}