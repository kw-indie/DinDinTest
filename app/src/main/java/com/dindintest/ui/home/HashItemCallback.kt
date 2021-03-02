package com.dindintest.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.dindintest.data.model.IDed

class HashItemCallback<T : IDed> : DiffUtil.ItemCallback<T>() {
	override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
		return oldItem.id == newItem.id
	}

	override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
		return oldItem.hashCode() == newItem.hashCode()
	}
}