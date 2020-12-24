package com.example.tvshowsapp.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowsapp.Adapter.TvShowAdapter
import com.example.tvshowsapp.model.TVShow
import com.example.tvshowsapp.R
import com.example.tvshowsapp.ViewModel.MostPopularTvShowsViewModel
import com.example.tvshowsapp.databinding.ActivityTvShowBinding
import com.example.tvshowsapp.listener.TvShowsListener

class TvShowActivity : AppCompatActivity(), TvShowsListener {
    lateinit var viewModel: MostPopularTvShowsViewModel
    lateinit var activityMainBinding: ActivityTvShowBinding
    var tvShows: List<TVShow> = ArrayList()
    lateinit var tvShowsAdapter: TvShowAdapter
    var currentPage = 1
    var totalAvailablePages = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show)

        init()

    }

    fun init() {
        activityMainBinding.tvShowsRecyclerView.setHasFixedSize(true)
        viewModel = ViewModelProvider(this).get(MostPopularTvShowsViewModel::class.java)
        tvShowsAdapter = TvShowAdapter(tvShows, this)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        activityMainBinding.tvShowsRecyclerView.layoutManager = linearLayoutManager
        activityMainBinding.tvShowsRecyclerView.adapter = tvShowsAdapter
        activityMainBinding.tvShowsRecyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!activityMainBinding.tvShowsRecyclerView.canScrollVertically(1)) {
                    if (currentPage <= totalAvailablePages) {
                        currentPage += 1
                        getMostPopularTvShows()
                    }
                }
            }
        })

        getMostPopularTvShows()

    }

    fun getMostPopularTvShows() {
        toggleLoading()
        viewModel.getMostPopularTvShows(currentPage).observe(this, Observer {
            toggleLoading()
            if (it != null) {
                totalAvailablePages = it.totalPages
                if (it.tvShows != null) {
                    val oldCount = tvShows.size
                    (tvShows as ArrayList).addAll(it.tvShows)
                    Log.e("tvShows", tvShows.size.toString())
                    tvShowsAdapter.notifyItemRangeInserted(oldCount, tvShows.size)
                }
            }
        })
    }

    fun toggleLoading() {
        if (currentPage == 1) {
            if (activityMainBinding.isLoading != null) {
                activityMainBinding.isLoading = false
            } else {
                activityMainBinding.isLoading = true
            }
        } else {
            if (activityMainBinding.isLoadingMore != null) {
                activityMainBinding.isLoadingMore = false
            } else {
                activityMainBinding.isLoadingMore = true
            }
        }
    }

    override fun onTVShowClicked(tvShow: TVShow) {
        val intent = Intent(applicationContext, TvShowDetailsActivity::class.java)
        intent.putExtra("tvShow", tvShow)
        startActivity(intent)
    }
}