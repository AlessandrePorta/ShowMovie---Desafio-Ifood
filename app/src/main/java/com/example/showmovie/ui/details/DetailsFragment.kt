package com.example.showmovie.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.showmovie.R
import com.example.showmovie.databinding.FragmentMoviesDetailsBinding
import com.example.showmovie.ui.MoviesActivity
import com.example.showmovie.ui.model.MovieVO
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailsFragment : Fragment(R.layout.fragment_movies_details) {

    private lateinit var binding: FragmentMoviesDetailsBinding
    private val args by navArgs<DetailsFragmentArgs>()
    private val detailsViewModel: DetailsViewModel by viewModel { parametersOf(args.id) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectUiState()
        getToolbarGone()
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailsViewModel.uiState.collect { state ->
                    when (state) {
                        DetailUiState.Error -> errorState()
                        DetailUiState.Loading -> loadingState()
                        is DetailUiState.Success -> successState(state.movie)
                    }
                }
            }
        }
    }

    private fun successState(movie: MovieVO) = with(binding) {
        tvMovieName.text = movie.title
        tvMovieDescription.text = movie.description
        Glide.with(root)
            .load("https://image.tmdb.org/t/p/original" + movie.img)
            .fallback(R.drawable.ic_launcher_foreground)
            .into(ivMovieImg)
    }

    private fun loadingState() {
        Toast.makeText(requireContext(), "Carregando filme", Toast.LENGTH_LONG).show()
    }

    private fun errorState() {
        Toast.makeText(requireContext(), "Erro ao carregar imagem :(", Toast.LENGTH_LONG).show()
    }

    private fun getToolbarGone() {
        (activity as MoviesActivity).getToolbarGone()
    }
}