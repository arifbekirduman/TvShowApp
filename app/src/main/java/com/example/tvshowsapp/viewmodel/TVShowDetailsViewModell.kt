package com.example.tvshowsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tvshowsapp.repositories.TVShowDetailsRepository
import com.example.tvshowsapp.responses.TVShowDetailsResponse

class TVShowDetailsViewModell : ViewModel() {
    private val tvShowDetailsRepository: TVShowDetailsRepository

    fun getTVShowDetails(tvShowId: String?): LiveData<TVShowDetailsResponse?> {
        return tvShowDetailsRepository.getTVShowDetails(tvShowId)
    }

    init {
        tvShowDetailsRepository = TVShowDetailsRepository()
    }
}