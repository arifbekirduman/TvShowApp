package com.example.tvshowsapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class TVShow : Serializable {

    @SerializedName("id")
    val id = 0

    @SerializedName("name")
    val name: String? = null

    @SerializedName("start_date")
    val startDate: String? = null

    @SerializedName("country")
    val country: String? = null

    @SerializedName("network")
    val network: String? = null

    @SerializedName("status")
    val status: String? = null

    @SerializedName("image_thumbnail_path")
    val thumbnail: String? = null
}