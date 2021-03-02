package com.dindintest.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dindintest.data.model.FeaturedAd
import com.dindintest.databinding.ItemFeaturedAdBinding

class FeaturedAdsAdapter(
	private val onAd: OnAdClick
) : ListAdapter<FeaturedAd, FeaturedAdsAdapter.AdViewHolder>(HashItemCallback()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder {
		return AdViewHolder(
			ItemFeaturedAdBinding.inflate(
				LayoutInflater.from(parent.context), parent, false
			).apply {
				root.setOnClickListener {
					onAd.click(ad!!.id)
				}
			}
		)
	}

	override fun onBindViewHolder(holder: AdViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	class AdViewHolder(
		private val binding: ItemFeaturedAdBinding
	) : RecyclerView.ViewHolder(binding.root) {

		fun bind(ad: FeaturedAd) {
			binding.ad = ad
			binding.executePendingBindings()
		}
	}

	fun interface OnAdClick {
		fun click(adId: Long)
	}
}