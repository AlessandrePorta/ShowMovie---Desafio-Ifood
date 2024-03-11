package com.example.showmovie.application

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend inline fun <T> safeApiCall(crossinline call: suspend () -> T): Result<T> {
    return runCatching {
        withContext(Dispatchers.IO) { call.invoke() }
    }
}

suspend inline fun <T> safeApiCallOrNull(crossinline call: suspend () -> T): T? {
    return safeApiCall(call).getOrElse { exception ->
        Log.e("exception", exception.message, exception)
        null
    }

}