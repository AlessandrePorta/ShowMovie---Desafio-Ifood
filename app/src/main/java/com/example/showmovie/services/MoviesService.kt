package com.example.showmovie.services

import com.example.showmovie.ui.model.MoviesDataResponse
import com.example.showmovie.ui.model.MoviesPageInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("discover/movie")
    suspend fun getMovies(@Query("page") page: Int?): MoviesPageInfo

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String?,
        @Query("page") page: Int?
    ): MoviesPageInfo

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id: Int): MoviesDataResponse
}