package com.dindintest.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MavericksView

abstract class BaseFragment : Fragment(), MavericksView {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		view.apply {
			ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
				val navBarInsets = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
				val config = view.context.resources.configuration
				val lp = view.layoutParams as ViewGroup.MarginLayoutParams
				when {
					config.orientation == Configuration.ORIENTATION_PORTRAIT -> {
						lp.bottomMargin = navBarInsets.bottom
					}
					config.layoutDirection == View.LAYOUT_DIRECTION_LTR -> {
						lp.rightMargin = navBarInsets.right
					}
					else -> {
						lp.leftMargin = navBarInsets.right
					}
				}
				setOnApplyWindowInsetsListener(null)
				WindowInsetsCompat.CONSUMED
			}
		}
	}
}