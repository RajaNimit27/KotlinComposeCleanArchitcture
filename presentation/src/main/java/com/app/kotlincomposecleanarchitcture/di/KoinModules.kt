package com.app.kotlincomposecleanarchitcture.di

import android.app.Application
import androidx.room.Room
import com.app.kotlincomposecleanarchitcture.ui.MainViewModel
import data.datasource.local.PostDao
import data.datasource.local.PostDataBase
import data.datasource.remote.ApiService
import data.datasource.remote.RemoteDataSource
import data.repository.PostRepositoryImpl
import domain.usecase.GetPostsUseCase
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun provideHttpClient(): OkHttpClient {
    return OkHttpClient
        .Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
}


fun provideConverterFactory(): GsonConverterFactory =
    GsonConverterFactory.create()


fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

fun provideService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

val networkModule= module {
    single { provideHttpClient() }
    single { provideConverterFactory() }
    single { provideRetrofit(get(),get()) }
    single { provideService(get()) }
}

val dataSourceModule = module {
    factory {  RemoteDataSource(get()) }
}

fun provideDataBase(application: Application): PostDataBase =
    Room.databaseBuilder(
        application,
        PostDataBase::class.java,
        "table_post"
    ).
    fallbackToDestructiveMigration().build()

fun provideDao(postDataBase: PostDataBase): PostDao = postDataBase.getPostDao()


val dataBaseModule= module {
    single { provideDataBase(get()) }
    single { provideDao(get()) }
}

val repositoryModule = module{
    single { PostRepositoryImpl(get(),get()) }
}

val useCaseModule = module {
    factory { GetPostsUseCase(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val allModule = listOf(networkModule, dataBaseModule, dataSourceModule, repositoryModule, useCaseModule,
    viewModelModule)
