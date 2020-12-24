package com.example.tvshowsapp.Api

import com.example.tvshowsapp.Responses.TVShowDetailsResponse
import com.example.tvshowsapp.Responses.TvShowsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("most-popular")
    fun getMostPopularTvShows(@Query("page") page : Int) : Call<TvShowsResponse>

    @GET("show-details")
    fun getTVShowDetails(@Query("q") tvShowId : String) : Call<TVShowDetailsResponse>
}