package com.example.showmovie

import com.example.showmovie.ui.model.MoviesDataResponse

class MovieDetailFactory {

    fun createResponse() = MoviesDataResponse(
        title = "Spider-Man",
        id = 1,
        adult = false,
        backdrop_path = "",
        genre_ids = listOf(1, 2),
        original_language = "EN",
        original_title = "Spider",
        overview = "Spider-man movie",
        popularity = 2.4,
        poster_path = "thumb here",
        release_date = "01/01/2001",
        video = false,
        vote_average = 5.0,
        vote_count = 5
    )
}