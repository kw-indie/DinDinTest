package com.dindintest.data.model

data class Cart(
	val items: List<CartItem> = listOf(),
	val note: String = ""
)