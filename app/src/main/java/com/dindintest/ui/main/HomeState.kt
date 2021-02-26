package com.dindintest.ui.main

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.dindintest.data.models.Cart
import com.dindintest.data.models.CartItem
import com.dindintest.data.models.FeaturedAd
import com.dindintest.data.models.Menu

data class HomeState(
    val featuredAds: Async<List<FeaturedAd>> = Uninitialized,
    val menus: Async<List<Menu>> = Uninitialized,
    val cart: Async<Cart> = Uninitialized,
    val addOrUpdateItemRequest: Async<Int> = Uninitialized,
    val removeItemRequest: Async<List<CartItem>> = Uninitialized,
    val setNoteRequest: Async<String> = Uninitialized,
    val checkoutRequest: Async<Unit> = Uninitialized
) : MavericksState