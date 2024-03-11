package com.example.showmovie.repository

import androidx.paging.PagingSource
import com.example.showmovie.ui.model.MovieVO

interface MoviesRepository {

    fun getMovies(): PagingSource<Int, MovieVO>

    fun searchMovies(query: String?): PagingSource<Int, MovieVO>

    suspend fun getMovieDetails(id: Int): MovieVO?

}