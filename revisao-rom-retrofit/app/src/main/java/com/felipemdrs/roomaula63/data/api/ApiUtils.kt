package com.felipemdrs.roomaula63.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiUtils {
    companion object {
        private const val BASE_URL = "https://age-of-empires-2-api.herokuapp.com/api/v1/"

        fun getRetrofitInstance(baseUrl: String = BASE_URL): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}