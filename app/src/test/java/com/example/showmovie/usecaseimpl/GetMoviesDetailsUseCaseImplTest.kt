package com.example.showmovie.usecaseimpl


import com.example.showmovie.MovieDetailFactory
import com.example.showmovie.repository.MoviesRepository
import com.example.showmovie.repository.impl.MoviesRepositoryImpl
import com.example.showmovie.services.MoviesService
import com.example.showmovie.usecase.impl.GetDetailsUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class GetMoviesDetailsUseCaseImplTest {
    private lateinit var repository: MoviesRepository

    private lateinit var useCase: GetDetailsUseCaseImpl

    private val fakeDetailResponse = MovieDetailFactory().createResponse()

    @Before
    fun setUp() {
        val api = mockk<MoviesService>()
        coEvery { api.getMovieDetails(any()) } returns fakeDetailResponse
        repository = MoviesRepositoryImpl(api)
        useCase = GetDetailsUseCaseImpl(repository)
    }

    @Test
    fun `when call useCase then return expected`() = runTest {
        val result = useCase(1)
        assertNotNull(result)
        assertEquals(result.id, 1)
        assertEquals(result.title, "Spider-Man")
        assertEquals(result.img, "thumb here")
        assertEquals(result.description, "Spider-man movie")
    }
}