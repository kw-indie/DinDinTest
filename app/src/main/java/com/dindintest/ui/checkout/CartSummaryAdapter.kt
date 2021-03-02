package com.dindintest.ui.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dindintest.databinding.ItemCartSummaryBinding

class CartSummaryAdapter : RecyclerView.Adapter<CartSummaryAdapter.CartSummaryViewHolder>() {

	var total: Float = 0f
		set(value) {
			if (value != field) {
				field = value
				notifyDataSetChanged()
			}
		}

	override fun getItemCount(): Int {
		return 1
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartSummaryViewHolder {
		return CartSummaryViewHolder(
			ItemCartSummaryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		)
	}

	override fun onBindViewHolder(holder: CartSummaryViewHolder, position: Int) {
		holder.bind(total)
	}

	class CartSummaryViewHolder(
		private val binding: ItemCartSummaryBinding
	) : RecyclerView.ViewHolder(binding.root) {

		fun bind(total: Float) {
			binding.total = total
			binding.executePendingBindings()
		}
	}
}