package com.example.socialapp.di

import com.example.data.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(
                        Interceptor { chain ->
                            val request = chain.request().newBuilder().addHeader(
                                    name = "X-Parse-Application-Id", value = APP_ID
                                ).addHeader(
                                    name = "X-Parse-REST-API-Key", value = REST_API_KEY
                                ).addHeader(
                                    name = "Content-Type", value = "application/json"
                                ).build()
                            return@Interceptor chain.proceed(request = request)
                        },
                    ).build()
            ).build()
    }

    @Provides
    fun provideUserService(
        retrofit: Retrofit,
    ): UserService = retrofit.create(UserService::class.java)

    companion object {
        private const val BASE_URL = "https://parseapi.back4app.com/"
        private const val APP_ID = "WWMmHRLyaMaDwCOBD1ytlHDhcFyxEAE6UBNPds1Y"
        private const val REST_API_KEY = "VBvqxKkaPoZ4FW267oAwoUmf2CWHknwPh6SD5msK"
    }
}