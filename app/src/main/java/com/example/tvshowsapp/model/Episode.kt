package com.example.tvshowsapp.model

import com.google.gson.annotations.SerializedName

class Episode {
    @SerializedName("season")
    val season: String? = null

    @SerializedName("episode")
    val episode: String? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("air_date")
    val airDate: String? = null
}