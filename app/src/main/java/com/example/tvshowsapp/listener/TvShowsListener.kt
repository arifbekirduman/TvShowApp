package com.example.tvshowsapp.listener

import com.example.tvshowsapp.model.TVShow

interface TvShowsListener {
    fun onTVShowClicked(tvShow : TVShow)
}