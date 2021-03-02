package com.dindintest.ui.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dindintest.databinding.ItemCartBinding
import com.dindintest.databinding.ItemPlaceholderPageBinding

class CheckoutPagerAdapter(
	private val cartItemAdapter: CartItemAdapter,
	private val cartSummaryAdapter: CartSummaryAdapter
) : RecyclerView.Adapter<CheckoutPagerAdapter.CheckoutViewHolder>() {

	override fun getItemCount(): Int {
		return 3 // pages
	}

	override fun getItemViewType(position: Int): Int {
		return if (position == 0) 0 else 1 // only 1 implementation: cart page
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = if (viewType == 0) {
			ItemCartBinding.inflate(inflater, parent, false).apply {
				val rootRv = root as RecyclerView
				val concatAdapter = ConcatAdapter(cartItemAdapter, cartSummaryAdapter)
				rootRv.apply {
					adapter = concatAdapter
					setHasFixedSize(true)
				}
			}
		} else {
			ItemPlaceholderPageBinding.inflate(inflater, parent, false)
		}
		return CheckoutViewHolder(binding)
	}

	override fun onBindViewHolder(holder: CheckoutViewHolder, position: Int) {}

	class CheckoutViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}