package com.example.showmovie.viewmodel

import com.example.showmovie.MainCoroutineRule
import com.example.showmovie.ui.MoviesViewModel
import com.example.showmovie.usecase.impl.GetMoviesPagingUseCaseImpl
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class MoviesViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val useCaseMock: GetMoviesPagingUseCaseImpl = mockk(relaxed = true)

    private val viewModel = MoviesViewModel(useCaseMock)

    @Test
    fun `when call searchMovies then should go to usecase`() = runTest {

    }

}