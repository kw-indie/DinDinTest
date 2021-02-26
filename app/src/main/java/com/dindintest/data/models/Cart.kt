package com.dindintest.data.models

data class Cart(
	val items: List<CartItem> = listOf(),
	val note: String = ""
)