package com.example.tvshowsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowsapp.adapter.TvShowAdapter.TvShowViewHolder
import com.example.tvshowsapp.R
import com.example.tvshowsapp.databinding.ItemContainerTvShowBinding
import com.example.tvshowsapp.listener.TvShowsListener
import com.example.tvshowsapp.model.TVShow

class TvShowAdapter(
    private val tvShows: List<TVShow>,
    private val tvShowsListener: TvShowsListener
) : RecyclerView.Adapter<TvShowViewHolder>() {
    private var layoutInflater: LayoutInflater? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val tvShowBinding: ItemContainerTvShowBinding = DataBindingUtil.inflate(
            layoutInflater!!, R.layout.item_container_tv_show, parent, false
        )
        return TvShowViewHolder(tvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bindTvShow(tvShows[position])
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

    inner class TvShowViewHolder(private val itemContainerTvShowBinding: ItemContainerTvShowBinding) :
        RecyclerView.ViewHolder(
            itemContainerTvShowBinding.root
        ) {
        fun bindTvShow(tvShow: TVShow?) {
            itemContainerTvShowBinding.tvShow = tvShow
            itemContainerTvShowBinding.executePendingBindings()
            itemContainerTvShowBinding.root.setOnClickListener {
                tvShowsListener.onTVShowClicked(
                    tvShow!!
                )
            }
        }
    }
}