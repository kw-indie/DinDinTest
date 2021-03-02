package com.dindintest.ui.checkout

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.dindintest.data.FoodRepo

class CheckoutViewModel(
	initState: CheckoutState,
	private val repo: FoodRepo
) : BaseMvRxViewModel<CheckoutState>(initState) {

	init {
		repo.getCart().execute { copy(cart = it) }
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

	companion object : MavericksViewModelFactory<CheckoutViewModel, CheckoutState> {
		override fun create(
			viewModelContext: ViewModelContext,
			state: CheckoutState
		): CheckoutViewModel? {
			val repo = FoodRepo
			return CheckoutViewModel(state, repo)
		}
	}
}