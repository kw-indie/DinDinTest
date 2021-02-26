package com.dindintest.data.models

data class Menu(
	val id: Long,
	val title: String,
	val items: List<Item>
)