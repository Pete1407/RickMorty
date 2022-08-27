package com.example.rickmorty.app.di

import com.example.rickmorty.BuildConfig
import com.example.rickmorty.app.data.remote.ServiceAPI
import com.example.rickmorty.app.data.utils.ConnectivityInterceptor
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        //val interceptor = HttpLoggingInterceptor()
        //interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(ConnectivityInterceptor()).build()
    }

    @Singleton
    @Provides
    fun provideMoshi():Moshi{
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return moshi
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient,moshi: Moshi):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASEURL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }


    @Singleton
    @Provides
    fun provideServiceAPI(retrofit: Retrofit):ServiceAPI{
        return retrofit.create(ServiceAPI::class.java)
    }

}