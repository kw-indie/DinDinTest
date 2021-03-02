package com.dindintest.ui.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dindintest.data.model.CartItem
import com.dindintest.databinding.ItemCartItemBinding
import com.dindintest.ui.home.HashItemCallback

class CartItemAdapter(
	private val onDelete: OnDeleteClick
) : ListAdapter<CartItem, CartItemAdapter.CartItemViewHolder>(HashItemCallback()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
		return CartItemViewHolder(
			ItemCartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).apply {
				delete.setOnClickListener {
					onDelete.click(cartItem!!.id)
				}
			}
		)
	}

	override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	class CartItemViewHolder(
		private val binding: ItemCartItemBinding
	) : RecyclerView.ViewHolder(binding.root) {

		fun bind(item: CartItem) {
			binding.cartItem = item
			binding.executePendingBindings()
		}
	}

	fun interface OnDeleteClick {
		fun click(itemId: Long)
	}
}