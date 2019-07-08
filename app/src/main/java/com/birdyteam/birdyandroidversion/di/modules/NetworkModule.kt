package com.birdyteam.birdyandroidversion.di.modules

import com.birdyteam.birdyandroidversion.network.api.AppRequests
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 07.07.2019
 */

@Module
object NetworkModule {

    private const val BASE_URL = "https://birdytestapi.azurewebsites.net"

    @JvmStatic
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @JvmStatic
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @JvmStatic
    @Provides
    @Singleton
    fun provideAppRequests(retrofit: Retrofit): AppRequests = retrofit.create(AppRequests::class.java)

}