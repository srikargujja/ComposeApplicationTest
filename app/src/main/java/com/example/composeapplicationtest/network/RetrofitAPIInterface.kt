package com.example.composeapplicationtest.network

import com.example.composeappplication.models.DataClassModelItem
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitAPIInterface {

    @GET("resource/s3k6-pzi2.json")
    suspend fun getData(): Response<List<DataClassModelItem>>
}