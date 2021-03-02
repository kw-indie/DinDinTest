package com.dindintest.ui.home

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.dindintest.data.FoodRepo

class HomeViewModel(
	initState: HomeState,
	private val repo: FoodRepo
) : BaseMvRxViewModel<HomeState>(initState) {

	init {
		repo.getFeaturedAds().execute { copy(featuredAds = it) }
		repo.getMenus().execute { copy(menus = it) }
	}

	fun addToCart(itemId: Long) = withState {
		repo.addToCartOrUpdate(itemId).execute {
			copy(addOrUpdateItemRequest = it)
		}
	}

	companion object : MavericksViewModelFactory<HomeViewModel, HomeState> {
		override fun create(viewModelContext: ViewModelContext, state: HomeState): HomeViewModel {
			val repo = FoodRepo
			return HomeViewModel(state, repo)
		}
	}
}