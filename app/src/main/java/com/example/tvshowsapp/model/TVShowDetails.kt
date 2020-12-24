package com.example.tvshowsapp.model

import com.google.gson.annotations.SerializedName

class TVShowDetails {
    @SerializedName("url")
    val url: String? = null

    @SerializedName("description")
    val description: String? = null

    @SerializedName("runtime")
    val runtime: String? = null

    @SerializedName("image_path")
    val image_path: String? = null

    @SerializedName("rating")
    val rating: String? = null

    @SerializedName("pictures")
    val pictures: Array<String>? = null

    @SerializedName("episodes")
    val episodes: List<Episode>? = null
}