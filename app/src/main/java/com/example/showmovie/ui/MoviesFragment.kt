package com.example.showmovie.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.showmovie.R
import com.example.showmovie.databinding.FragmentMoviesBinding
import com.example.showmovie.ui.adapter.ListLoadStateAdapter
import com.example.showmovie.ui.adapter.MoviesAdapter
import com.example.showmovie.ui.adapter.MoviesAdapter.Companion.MOVIES_VIEW_TYPE
import com.example.showmovie.ui.model.MovieVO
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(R.layout.fragment_movies) {

    private lateinit var binding: FragmentMoviesBinding

    private val movieAdapter = MoviesAdapter(onMovieClicked = ::navigateToMovieDetails)

    private val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        init()
    }

    private fun init() {
        (activity as MoviesActivity).getToolbarShow()
        setupAdapter()
        getMovies()
        setupMenu()
    }

    private fun getMovies() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                moviesViewModel.pagingDataFlow.collect {
                    movieAdapter.submitData(it)
                }
            }
        }
    }

    private fun setupAdapter() {
        with(binding.rvContainer) {
            val concatAdapter = movieAdapter.withLoadStateFooter(
                footer = ListLoadStateAdapter {
                    movieAdapter.retry()
                }
            )
            layoutManager = setupLayoutManager(concatAdapter)
            adapter = concatAdapter
        }
    }

    private fun setupLayoutManager(concatAdapter: ConcatAdapter): GridLayoutManager {
        return GridLayoutManager(requireContext(), FULL_SPAN_COUNT).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (concatAdapter.getItemViewType(position) == MOVIES_VIEW_TYPE) {
                        FULL_SPAN_COUNT
                    } else {
                        SINGLE_SPAN_COUNT
                    }
                }
            }
        }
    }

    private fun setupMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menu.clear()
                menuInflater.inflate(R.menu.menu_search, menu)
                val searchItem = menu.findItem(R.id.search)
                val searchView = searchItem.actionView as SearchView
                searchItem.setIcon(R.drawable.ic_search)
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        moviesViewModel.search(newText)
                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.search -> {
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner)
    }

    private fun navigateToMovieDetails(movieVO: MovieVO) {
        findNavController()
            .navigate(
                MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment(
                    movieVO.id.toString()
                )
            )
    }

    companion object {
        private const val FULL_SPAN_COUNT = 2
        private const val SINGLE_SPAN_COUNT = 1
    }
}