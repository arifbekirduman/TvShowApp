package com.example.tvshowsapp.api

class ApiUtils {
    companion object{
        val BASE_URL = "https://www.episodate.com/api/"
        fun getAPIService(): ApiService? {
            return ApiClient().getClient(BASE_URL)!!.create(ApiService::class.java)
        }
    }
}