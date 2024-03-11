package com.example.showmovie.utils

import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.Flow

fun <T> Flow<T>.toLiveData() = liveData { this@toLiveData.collect { emit(it) } }