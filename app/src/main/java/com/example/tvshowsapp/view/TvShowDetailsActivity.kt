package com.example.tvshowsapp.view

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.tvshowsapp.adapter.ImageSliderAdapter
import com.example.tvshowsapp.R
import com.example.tvshowsapp.viewmodel.TVShowDetailsViewModell
import com.example.tvshowsapp.databinding.ActivityTvShowDetailsBinding
import com.example.tvshowsapp.model.TVShow

class TvShowDetailsActivity : AppCompatActivity() {
    lateinit var activityTVShowDetailsBinding: ActivityTvShowDetailsBinding
    lateinit var viewModell: TVShowDetailsViewModell
    lateinit var tvShow: TVShow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTVShowDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_tv_show_details)

        init()

    }

    private fun init() {
        tvShow = intent.getSerializableExtra("tvShow") as TVShow
        viewModell = ViewModelProvider(this).get(TVShowDetailsViewModell::class.java)
        getTVShowDetails();

        activityTVShowDetailsBinding.imageBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getTVShowDetails() {
        activityTVShowDetailsBinding.isLoading = true

        viewModell.getTVShowDetails(tvShow.id.toString()).observe(this, {
            activityTVShowDetailsBinding.isLoading = false
            if (it!!.tvShowDetails != null) {
                if (it.tvShowDetails!!.pictures != null) {
                    loadImageSlider(it.tvShowDetails.pictures!!)
                    activityTVShowDetailsBinding.tvShowImageUrl = it.tvShowDetails.image_path
                    activityTVShowDetailsBinding.imageTVShow.visibility = View.VISIBLE
                    activityTVShowDetailsBinding.showDescription = it.tvShowDetails.description
                    setupSliderIndicator(it.tvShowDetails.pictures.size)
                    activityTVShowDetailsBinding.textDescription.visibility = View.VISIBLE
                    activityTVShowDetailsBinding.textReadMore.visibility = View.VISIBLE
                    activityTVShowDetailsBinding.textReadMore.setOnClickListener {
                        if (activityTVShowDetailsBinding.textReadMore.text.toString()
                                .equals("Read More")
                        ) {
                            activityTVShowDetailsBinding.textDescription.maxLines = Int.MAX_VALUE
                            activityTVShowDetailsBinding.textDescription.ellipsize = null
                            activityTVShowDetailsBinding.textReadMore.text = "Read Less"
                        } else {
                            activityTVShowDetailsBinding.textDescription.maxLines = 4
                            activityTVShowDetailsBinding.textDescription.ellipsize =
                                TextUtils.TruncateAt.END
                            activityTVShowDetailsBinding.textReadMore.text = "Read More"
                        }
                    }

                    loadBasicTvShowDetails()
                }
            }

        })
    }

    fun loadImageSlider(sliderImages: Array<String>) {
        activityTVShowDetailsBinding.viewPagerActivityShowDetails.offscreenPageLimit = 1;
        activityTVShowDetailsBinding.viewPagerActivityShowDetails.adapter =
            ImageSliderAdapter(sliderImages)
        activityTVShowDetailsBinding.viewPagerActivityShowDetails.visibility = View.VISIBLE
        activityTVShowDetailsBinding.viewFadingEdge.visibility = View.VISIBLE
        activityTVShowDetailsBinding.viewPagerActivityShowDetails.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setCurrentSliderIndicator(position)
                }
            })
    }

    fun setupSliderIndicator(count: Int) {
        val indicators: Array<ImageView?> = arrayOfNulls(count)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]!!.setImageDrawable(
                ContextCompat
                    .getDrawable(
                        applicationContext,
                        R.drawable.background_slider_indicator_inactive
                    )
            )

            indicators[i]!!.layoutParams = layoutParams
            activityTVShowDetailsBinding.layoutSliderIndicators.addView(indicators[i])
        }
        activityTVShowDetailsBinding.layoutSliderIndicators.visibility = View.VISIBLE
        setCurrentSliderIndicator(0)
    }

    fun setCurrentSliderIndicator(position: Int) {
        val childCount = activityTVShowDetailsBinding.layoutSliderIndicators.childCount
        for (i in 0 until childCount) {
            val imageView =
                activityTVShowDetailsBinding.layoutSliderIndicators.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.background_slider_indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.background_slider_indicator_inactive
                    )
                )
            }
        }
    }

    fun loadBasicTvShowDetails() {
        activityTVShowDetailsBinding.showName = tvShow.name
        activityTVShowDetailsBinding.networkCountry = tvShow.network + " (" +
                tvShow.country + ")"
        activityTVShowDetailsBinding.status = tvShow.status
        activityTVShowDetailsBinding.startedDate = tvShow.startDate

        activityTVShowDetailsBinding.textName.visibility = View.VISIBLE
        activityTVShowDetailsBinding.textNetworkCountry.visibility = View.VISIBLE
        activityTVShowDetailsBinding.textStatus.visibility = View.VISIBLE
        activityTVShowDetailsBinding.textStarted.visibility = View.VISIBLE

    }
}