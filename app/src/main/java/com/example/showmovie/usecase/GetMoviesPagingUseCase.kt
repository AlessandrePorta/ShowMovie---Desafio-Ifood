package com.example.showmovie.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.showmovie.ui.model.MovieVO
import kotlinx.coroutines.flow.Flow

interface GetMoviesPagingUseCase {
    operator fun invoke(query: String, pagingConfig: PagingConfig): Flow<PagingData<MovieVO>>
}