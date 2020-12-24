package com.example.tvshowsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tvshowsapp.repositories.MostPopularTvShowsRepository
import com.example.tvshowsapp.responses.TvShowsResponse

class MostPopularTvShowsViewModel : ViewModel() {
    private var mostPopularTvShowsRepository: MostPopularTvShowsRepository

    fun getMostPopularTvShows(page: Int): LiveData<TvShowsResponse?> {
        return mostPopularTvShowsRepository.getMostPopulerTvShows(page)
    }

    init {
        mostPopularTvShowsRepository = MostPopularTvShowsRepository()
    }
}