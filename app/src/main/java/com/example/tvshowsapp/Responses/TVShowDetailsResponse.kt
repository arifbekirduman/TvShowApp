package com.example.tvshowsapp.Responses

import com.example.tvshowsapp.model.TVShowDetails
import com.google.gson.annotations.SerializedName

class TVShowDetailsResponse {
    @SerializedName("tvShow")
    val tvShowDetails: TVShowDetails? = null
}