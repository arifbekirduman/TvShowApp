package com.example.tvshowsapp.api

import com.example.tvshowsapp.responses.TVShowDetailsResponse
import com.example.tvshowsapp.responses.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("most-popular")
    fun getMostPopularTvShows(@Query("page") page : Int) : Call<TvShowsResponse>

    @GET("show-details")
    fun getTVShowDetails(@Query("q") tvShowId : String) : Call<TVShowDetailsResponse>
}