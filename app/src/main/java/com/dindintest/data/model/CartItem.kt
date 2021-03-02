package com.dindintest.data.model

data class CartItem(
	val item: Item,
	val qty: Int
) : IDed {
	override val id: Long
		get() = item.id
}