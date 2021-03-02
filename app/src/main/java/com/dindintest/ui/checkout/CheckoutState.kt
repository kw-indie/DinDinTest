package com.dindintest.ui.checkout

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.dindintest.data.model.Cart

data class CheckoutState(
	val cart: Async<Cart> = Uninitialized,
	val removeItemRequest: Async<Cart> = Uninitialized,
	val setNoteRequest: Async<String> = Uninitialized,
	val checkoutRequest: Async<Unit> = Uninitialized
) : MavericksState