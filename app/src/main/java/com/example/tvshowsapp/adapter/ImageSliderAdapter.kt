package com.example.tvshowsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowsapp.adapter.ImageSliderAdapter.ImageSliderViewHolder
import com.example.tvshowsapp.R
import com.example.tvshowsapp.databinding.ItemContainerSilderImageBinding

class ImageSliderAdapter(private val sliderImages: Array<String>) :
    RecyclerView.Adapter<ImageSliderViewHolder>() {
    private var layoutInflater: LayoutInflater? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSliderViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val silderImageBinding: ItemContainerSilderImageBinding = DataBindingUtil
            .inflate(layoutInflater!!, R.layout.item_container_silder_image, parent, false)
        return ImageSliderViewHolder(silderImageBinding)
    }

    override fun onBindViewHolder(holder: ImageSliderViewHolder, position: Int) {
        holder.bindSliderImage(sliderImages[position])
    }

    override fun getItemCount(): Int {
        return sliderImages.size
    }

    class ImageSliderViewHolder(private val itemContainerSilderImageBinding: ItemContainerSilderImageBinding) :
        RecyclerView.ViewHolder(
            itemContainerSilderImageBinding.root
        ) {
        fun bindSliderImage(imageURL: String?) {
            itemContainerSilderImageBinding.imageURL = imageURL
        }
    }
}