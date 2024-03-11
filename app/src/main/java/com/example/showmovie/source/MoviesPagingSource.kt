package com.example.showmovie.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.showmovie.ui.model.MovieVO
import com.example.showmovie.ui.model.MoviesPageInfo
import com.example.showmovie.utils.toModel

class MoviesPagingSource(
    private val fetchMovies: suspend (Int) -> MoviesPageInfo,
) : PagingSource<Int, MovieVO>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieVO> {
        return try {
            val offset = params.key ?: 1

            val response = fetchMovies(offset)
            val responseOffset = response.page
            val total = response.total_pages

            LoadResult.Page(
                data = response.results.map { it.toModel() },
                prevKey = null,
                nextKey = if (responseOffset < total) {
                    responseOffset + LIMIT
                } else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieVO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    companion object {
        private const val LIMIT = 1
    }
}