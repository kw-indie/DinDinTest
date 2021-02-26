package com.dindintest.ui.main

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.dindintest.App
import com.dindintest.data.FoodRepo

class HomeViewModel(
    initState: HomeState,
    private val repo: FoodRepo
) : BaseMvRxViewModel<HomeState>(initState) {

	init {
		repo.getFeaturedAds().execute { copy(featuredAds = it) }
		repo.getMenus().execute { copy(menus = it) }
		repo.getCart().execute { copy(cart = it) }
	}

	fun addToCart(itemId: Long) = withState {
		repo.addToCartOrUpdate(itemId).execute {
			copy(addOrUpdateItemRequest = it)
		}
	}

	fun removeFromCart(itemId: Long) = withState {
		repo.removeFromCart(itemId).execute {
			copy(removeItemRequest = it)
		}
	}

	fun setCartNote(note: String) = withState {
		repo.setCartNote(note).execute {
			copy(setNoteRequest = it)
		}
	}

	fun checkout() = withState {
		repo.checkout().execute {
			copy(checkoutRequest = it)
		}
	}

	companion object : MavericksViewModelFactory<HomeViewModel, HomeState> {
		override fun create(viewModelContext: ViewModelContext, state: HomeState): HomeViewModel {
			val repo = viewModelContext.app<App>().repo
			return HomeViewModel(state, repo)
		}
	}
}