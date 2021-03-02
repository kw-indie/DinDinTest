package com.dindintest.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.core.view.children
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dindintest.data.model.Menu
import com.dindintest.databinding.ItemMenuBinding
import com.google.android.material.chip.Chip

class MenusAdapter(
	private val buyListener: MenuItemAdapter.OnBuyClick
) : ListAdapter<Menu, MenusAdapter.MenuViewHolder>(HashItemCallback()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
		return MenuViewHolder(
			ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false).apply {
				val itemAdapter = MenuItemAdapter(buyListener)
				val listener = CompoundButton.OnCheckedChangeListener { chip, isChecked ->
					itemAdapter.filter(chip.text.toString(), isChecked)
				}
				items.adapter = itemAdapter
				filters.children.forEach {
					val chip = it as Chip
					chip.setOnCheckedChangeListener(listener)
				}
			}
		)
	}

	override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	class MenuViewHolder(
		private val binding: ItemMenuBinding
	) : RecyclerView.ViewHolder(binding.root) {

		fun bind(menu: Menu) {
			binding.menu = menu
			binding.executePendingBindings()
		}
	}

}