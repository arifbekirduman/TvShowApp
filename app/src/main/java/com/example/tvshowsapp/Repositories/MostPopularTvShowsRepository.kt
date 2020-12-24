package com.example.tvshowsapp.Repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tvshowsapp.Api.ApiService
import com.example.tvshowsapp.Api.ApiUtils.Companion.getAPIService
import com.example.tvshowsapp.Responses.TvShowsResponse
import org.jetbrains.annotations.NonNls
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MostPopularTvShowsRepository {
    private var apiService: ApiService?
    fun getMostPopulerTvShows(page: Int): LiveData<TvShowsResponse?> {
        val data = MutableLiveData<TvShowsResponse?>()
        apiService!!.getMostPopularTvShows(page).enqueue(object : Callback<TvShowsResponse?> {
            override fun onResponse(
                call: Call<TvShowsResponse?>,
                @NonNls response: Response<TvShowsResponse?>
            ) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<TvShowsResponse?>, t: Throwable) {
                data.value = null
                Log.e("TvShowsRepository", t.message!!)
            }
        })
        return data
    }

    init {
        apiService = getAPIService()
    }
}