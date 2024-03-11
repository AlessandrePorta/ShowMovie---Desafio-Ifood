package com.example.showmovie.application

import com.example.showmovie.BuildConfig
import com.example.showmovie.repository.MoviesRepository
import com.example.showmovie.repository.impl.MoviesRepositoryImpl
import com.example.showmovie.services.MoviesService
import com.example.showmovie.ui.MoviesViewModel
import com.example.showmovie.ui.details.DetailsViewModel
import com.example.showmovie.usecase.GetDetailsUseCase
import com.example.showmovie.usecase.GetMoviesPagingUseCase
import com.example.showmovie.usecase.impl.GetDetailsUseCaseImpl
import com.example.showmovie.usecase.impl.GetMoviesPagingUseCaseImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val HTTP_CLIENT_BASE = "HTTP_CLIENT_BASE"

private const val CONNECT_TIMEOUT = 5L

private const val READ_TIMEOUT = 30L
private const val WRITE_TIMEOUT = 30L

private const val HTTP_CLIENT_MOVIES = "HTTP_CLIENT_MOVIES"

val retrofitModule = module {

    factory<AuthorizationInterceptor> {
        AuthorizationInterceptor(
            apiKey = BuildConfig.API_KEY
        )
    }

    factory<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single<OkHttpClient>(named(HTTP_CLIENT_BASE)) {
        OkHttpClient
            .Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS).build()
    }

    factory<OkHttpClient>(named(HTTP_CLIENT_MOVIES)) {
        get<OkHttpClient>(named(HTTP_CLIENT_BASE))
            .newBuilder()
            .addInterceptor(get<AuthorizationInterceptor>())
            .build()
    }

    factory<Converter.Factory> { GsonConverterFactory.create() }

    factory<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get(named(HTTP_CLIENT_MOVIES)))
            .addConverterFactory(get())
            .build()
    }

    factory { get<Retrofit>().create(MoviesService::class.java) }

}

val appModule = module {
    factory<MoviesRepository> { MoviesRepositoryImpl(get()) }
    factory<GetMoviesPagingUseCase> { GetMoviesPagingUseCaseImpl(get()) }
    factory<GetDetailsUseCase> { GetDetailsUseCaseImpl(get()) }
}

val viewModule = module {
    viewModel { MoviesViewModel(get()) }
    viewModel { params -> DetailsViewModel(params.get(), get()) }
}
