package com.example.showmovie

import com.example.showmovie.ui.model.MovieVO

class MovieFactory {

    fun createList() = listOf(
        MovieVO(
            id = 1,
            title = "Spider",
            description = "Spider-man",
            img = ""
        ),
        MovieVO(
            id = 2,
            title = "Batman",
            description = "Batman",
            img = ""
        ),
        MovieVO(
            id = 3,
            title = "Iron-man",
            description = "Iron-man",
            img = ""
        ),
    )
}