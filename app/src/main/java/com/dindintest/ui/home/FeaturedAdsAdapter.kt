package com.dindintest.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dindintest.data.models.FeaturedAd
import com.dindintest.databinding.ItemFeaturedAdBinding

class FeaturedAdsAdapter :
	ListAdapter<FeaturedAd, FeaturedAdsAdapter.AdViewHolder>(DiffCallback()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder {
		return AdViewHolder(
			ItemFeaturedAdBinding.inflate(
				LayoutInflater.from(parent.context), parent, false
			)
		)
	}

	override fun onBindViewHolder(holder: AdViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	private class DiffCallback : DiffUtil.ItemCallback<FeaturedAd>() {
		override fun areItemsTheSame(oldItem: FeaturedAd, newItem: FeaturedAd): Boolean {
			return oldItem.id == newItem.id
		}

		override fun areContentsTheSame(oldItem: FeaturedAd, newItem: FeaturedAd): Boolean {
			return oldItem.id == newItem.id
		}
	}

	class AdViewHolder(
		private val binding: ItemFeaturedAdBinding
	) : RecyclerView.ViewHolder(binding.root) {

		fun bind(ad: FeaturedAd) {
			binding.ad = ad
			binding.executePendingBindings()
		}
	}
}