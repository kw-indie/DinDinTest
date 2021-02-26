package com.dindintest

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.dindintest.data.FoodRepo

class App : Application() {

	val repo = FoodRepo()

	override fun onCreate() {
		super.onCreate()

		Mavericks.initialize(this)
	}
}