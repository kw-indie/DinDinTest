package com.dindintest.ui.home

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized
import com.dindintest.data.model.FeaturedAd
import com.dindintest.data.model.Menu

data class HomeState(
    val featuredAds: Async<List<FeaturedAd>> = Uninitialized,
    val menus: Async<List<Menu>> = Uninitialized,
    val addOrUpdateItemRequest: Async<Int> = Uninitialized
) : MavericksState