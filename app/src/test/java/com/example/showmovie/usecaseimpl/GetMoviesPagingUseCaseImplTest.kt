package com.example.showmovie.usecaseimpl

import androidx.paging.PagingConfig
import com.example.showmovie.MovieFactory
import com.example.showmovie.PagingSourceFactory
import com.example.showmovie.repository.MoviesRepository
import com.example.showmovie.usecase.impl.GetMoviesPagingUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetMoviesPagingUseCaseImplTest {

    private lateinit var repository: MoviesRepository

    private lateinit var useCase: GetMoviesPagingUseCaseImpl

    private val movies = MovieFactory().createList()

    private val fakePagingSource = PagingSourceFactory().create(movies)

    @Before
    fun setUp() {
        repository = mockk()
        coEvery { repository.getMovies() } returns fakePagingSource
        coEvery { repository.searchMovies(any()) } returns fakePagingSource
        useCase = GetMoviesPagingUseCaseImpl(repository)
    }

    @Test
    fun `when call useCase then getMovies should be called`() = runTest {
        useCase.invoke(
            query = "",
            pagingConfig = PagingConfig(20)
        ).first()

        verify(exactly = 1) { repository.getMovies() }
    }

    @Test
    fun `when call useCase with query then searchMovies should be called with query `() = runTest {
        val query = "test"
        useCase.invoke(
            query = query,
            pagingConfig = PagingConfig(20)
        ).first()

        verify(exactly = 1) { repository.searchMovies(query) }
    }
}