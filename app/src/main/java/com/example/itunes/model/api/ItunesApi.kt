package com.example.itunes.model.api

import com.example.itunes.interfaces.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItunesApi {
    companion object {
        private var retrofit: Retrofit?=null
        fun getRetroInstance(): Retrofit {
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit!!
        }
    }
}