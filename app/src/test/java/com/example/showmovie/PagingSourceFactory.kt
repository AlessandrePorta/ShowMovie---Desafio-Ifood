package com.example.showmovie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.showmovie.ui.model.MovieVO

class PagingSourceFactory {

    fun create(movies: List<MovieVO>) = object : PagingSource<Int, MovieVO>() {
        override fun getRefreshKey(state: PagingState<Int, MovieVO>) = 1

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieVO> {
            return LoadResult.Page(
                data = movies,
                prevKey = null,
                nextKey = 20
            )
        }
    }
}