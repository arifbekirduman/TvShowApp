package com.example.tvshowsapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tvshowsapp.Repositories.MostPopularTvShowsRepository
import com.example.tvshowsapp.Responses.TvShowsResponse

class MostPopularTvShowsViewModel : ViewModel() {
    private var mostPopularTvShowsRepository: MostPopularTvShowsRepository

    fun getMostPopularTvShows(page: Int): LiveData<TvShowsResponse?> {
        return mostPopularTvShowsRepository.getMostPopulerTvShows(page)
    }

    init {
        mostPopularTvShowsRepository = MostPopularTvShowsRepository()
    }
}