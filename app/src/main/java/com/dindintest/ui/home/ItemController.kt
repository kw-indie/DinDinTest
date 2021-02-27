package com.dindintest.ui.home

import com.airbnb.epoxy.TypedEpoxyController
import com.dindintest.data.models.Item
import com.dindintest.menuItem

class ItemController : TypedEpoxyController<List<Item>>() {

	override fun buildModels(items: List<Item>) {
		items.forEach {
			menuItem {
				id(it.id)
				item(it)
			}
		}
	}
}