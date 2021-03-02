package com.dindintest.data.model

data class Item(
	override val id: Long,
	val imgUrl: String,
	val name: String,
	val desc: String,
	val price: Float,
	val prop1: String,
	val prop2: String,
	val options: List<String>,
	val tags: List<String>
) : IDed