package com.example.tvshowsapp.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tvshowsapp.api.ApiService
import com.example.tvshowsapp.api.ApiUtils.Companion.getAPIService
import com.example.tvshowsapp.responses.TVShowDetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowDetailsRepository {
    private var apiService: ApiService?
    fun getTVShowDetails(tvShowId: String?): LiveData<TVShowDetailsResponse?> {
        val data = MutableLiveData<TVShowDetailsResponse?>()
        apiService!!.getTVShowDetails(tvShowId!!)
            .enqueue(object : Callback<TVShowDetailsResponse?> {
                override fun onResponse(
                    call: Call<TVShowDetailsResponse?>,
                    response: Response<TVShowDetailsResponse?>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(call: Call<TVShowDetailsResponse?>, t: Throwable) {
                    Log.e("TVShowDetailsRepoEr", t.message!!)
                    data.value = null
                }
            })
        return data
    }

    init {
        apiService = getAPIService()
    }
}