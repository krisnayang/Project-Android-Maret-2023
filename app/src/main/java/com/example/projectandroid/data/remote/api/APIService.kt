package com.example.projectandroid.data.remote.api

import com.example.projectandroid.data.remote.remotedatasource.CharactersResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val BASE_URL = "https://valorant-api.com/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// TODO: Build a Retrofit object with the Moshi converter
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface APIService {
    @GET("agents")
    suspend fun getAgents(): CharactersResponse
}

// TODO: Create an object that provides a lazy-initialized retrofit service
object Api{
    val retrofitService: APIService by lazy {
        retrofit.create(APIService::class.java)
    }
}