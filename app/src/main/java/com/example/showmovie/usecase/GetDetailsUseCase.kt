package com.example.showmovie.usecase

import com.example.showmovie.ui.model.MovieVO

interface GetDetailsUseCase {

    suspend operator fun invoke(id: Int): MovieVO?
}