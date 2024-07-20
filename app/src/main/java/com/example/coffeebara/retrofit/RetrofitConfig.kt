package com.example.coffeebara.retrofit

import com.example.coffeebara.common.BaseResponse
import com.example.coffeebara.data.dto.CreateUserRequest
import com.example.coffeebara.data.dto.CreateUserResponseResult
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    @POST("/user")
    suspend fun createUser(@Body createUserRequest: CreateUserRequest) : Response<BaseResponse<CreateUserResponseResult>>
}

object RetrofitClient{
    private const val BASE_URL = "http://203.252.136.226:8080"

    private val client = OkHttpClient.Builder().build()

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    val getAPIService : APIService = retrofit.create(APIService::class.java)
}