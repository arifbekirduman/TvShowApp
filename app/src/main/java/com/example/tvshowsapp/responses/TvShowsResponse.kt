package com.example.tvshowsapp.responses

import com.example.tvshowsapp.model.TVShow
import com.google.gson.annotations.SerializedName

class TvShowsResponse {
    @SerializedName("page")
    val page = 0

    @SerializedName("pages")
    val totalPages = 0

    @SerializedName("tv_shows")
    val tvShows: List<TVShow>? = null
}