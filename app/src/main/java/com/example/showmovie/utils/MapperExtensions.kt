package com.example.showmovie.utils

import com.example.showmovie.ui.model.MovieVO
import com.example.showmovie.ui.model.MoviesDataResponse

fun MoviesDataResponse.toModel() = MovieVO(
    id = id,
    title = title,
    description = overview,
    img = poster_path
)