package com.example.showmovie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.showmovie.R
import com.example.showmovie.databinding.ActivityMoviesBinding

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.fcvMovies) as NavHostFragment).navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        init()
    }

    private fun init() {
        setupNavController(binding.toolbarApp)
        setSupportActionBar(binding.toolbarApp)
    }

    private fun setupNavController(toolbar: Toolbar) {
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isTopLevelDestinations =
                appBarConfiguration.topLevelDestinations.contains(destination.id)
            if (!isTopLevelDestinations) {
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
                toolbar.setNavigationOnClickListener {
                    navController.popBackStack()
                }
            }
        }
    }

    fun getToolbarGone() {
        supportActionBar?.hide()
    }

    fun getToolbarShow() {
        supportActionBar?.show()
    }
}