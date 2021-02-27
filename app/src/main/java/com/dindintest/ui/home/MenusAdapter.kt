package com.dindintest.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dindintest.data.models.Menu
import com.dindintest.databinding.ItemMenuBinding

class MenusAdapter : ListAdapter<Menu, MenusAdapter.MenuViewHolder>(DiffCallback()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
		val controller = ItemController()
		return MenuViewHolder(
			ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false),
			controller
		)
	}

	override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	private class DiffCallback : DiffUtil.ItemCallback<Menu>() {
		override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean {
			return oldItem.id == newItem.id
		}

		override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean {
			return oldItem.id == newItem.id
		}
	}

	class MenuViewHolder(
		private val binding: ItemMenuBinding,
		private val controller: ItemController
	) : RecyclerView.ViewHolder(binding.root) {

		init {
			binding.items.adapter = controller.adapter
		}

		fun bind(m: Menu) {
			binding.apply {
				menu = m
				controller.setData(m.items)
				executePendingBindings()
			}
		}
	}
}