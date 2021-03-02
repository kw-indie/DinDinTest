package com.dindintest.data.model

data class Menu(
	override val id: Long,
	val title: String,
	val items: List<Item>
) : IDed {

	val tags = items.flatMap { it.tags }.distinct()
}