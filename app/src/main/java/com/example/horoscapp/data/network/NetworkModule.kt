package com.example.horoscapp.data.network

import com.example.horoscapp.BuildConfig.BASE_URL
import com.example.horoscapp.data.RepositoryImpl
import com.example.horoscapp.data.core.interceptors.AuthInterceptor
import com.example.horoscapp.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().setLevel(BODY)
        return OkHttpClient
            .Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideHoroscopeAPIService(retrofit: Retrofit): HoroscopeAPIService {
        return retrofit.create(HoroscopeAPIService::class.java)
    }

    @Provides
    fun provideRepository(apiService: HoroscopeAPIService): Repository {
        return RepositoryImpl(apiService)
    }
}