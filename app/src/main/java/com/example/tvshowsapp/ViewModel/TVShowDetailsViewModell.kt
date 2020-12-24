package com.example.tvshowsapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tvshowsapp.Repositories.TVShowDetailsRepository
import com.example.tvshowsapp.Responses.TVShowDetailsResponse

class TVShowDetailsViewModell : ViewModel() {
    private val tvShowDetailsRepository: TVShowDetailsRepository

    fun getTVShowDetails(tvShowId: String?): LiveData<TVShowDetailsResponse?> {
        return tvShowDetailsRepository.getTVShowDetails(tvShowId)
    }

    init {
        tvShowDetailsRepository = TVShowDetailsRepository()
    }
}