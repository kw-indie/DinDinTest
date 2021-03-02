package com.dindintest.data.model

import com.dindintest.util.sumOf

data class Cart(
	val items: List<CartItem> = listOf(),
	val note: String = ""
) {
	val total = items.sumOf { it.item.price * it.qty }
}