package com.example.composeapplicationtest.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitAPIBuilder {

    companion object {
        var retrofitService: RetrofitAPIInterface? = null
        fun retrofitBuilderCall(): RetrofitAPIInterface {
            if(retrofitService == null) {
                val retrofit = Retrofit.Builder().baseUrl("https://data.cityofnewyork.us/").addConverterFactory(GsonConverterFactory.create()).build()
                retrofitService = retrofit.create(RetrofitAPIInterface::class.java)
            }
            return retrofitService!!
        }

    }

}