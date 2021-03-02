package com.dindintest.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dindintest.data.model.Item
import com.dindintest.databinding.ItemMenuItemBinding

class MenuItemAdapter(
	private val onBuy: OnBuyClick
) : ListAdapter<Item, MenuItemAdapter.ItemViewHolder>(HashItemCallback()) {

	private var unfiltered: List<Item>? = null
	private val filters = mutableListOf<String>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
		return ItemViewHolder(
			ItemMenuItemBinding.inflate(
				LayoutInflater.from(parent.context), parent, false
			).apply {
				addToCart.setOnClickListener {
					onBuy.click(item!!.id)
				}
			}
		)
	}

	override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	fun filter(tag: String, checked: Boolean) {
		if (checked) filters.add(tag) else filters.remove(tag)
		if (filters.isEmpty()) {
			submitList(unfiltered)
			unfiltered = null
		} else {
			if (unfiltered == null) {// first filter operation
				unfiltered = currentList
			}
			val newList = unfiltered?.filter { item ->
				item.tags.any { filters.contains(it) }
			}
			submitList(newList)
		}
	}

	class ItemViewHolder(
		private val binding: ItemMenuItemBinding
	) : RecyclerView.ViewHolder(binding.root) {

		fun bind(item: Item) {
			binding.item = item
			binding.executePendingBindings()
		}
	}

	fun interface OnBuyClick {
		fun click(itemId: Long)
	}
}