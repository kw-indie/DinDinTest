package com.dindintest.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import com.dindintest.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(), MavericksView {

	private val viewModel: HomeViewModel by activityViewModel()
	private lateinit var binding: FragmentHomeBinding

	private val TAG = javaClass.simpleName

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {

		binding = FragmentHomeBinding.inflate(inflater, container, false).apply {

			root.apply {
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

			val featuredAdsAdapter = FeaturedAdsAdapter()
			featuredAds.apply {
				adapter = featuredAdsAdapter
				offscreenPageLimit = 2
			}
			TabLayoutMediator(featuredAdDots, featuredAds) { _, _ -> }.attach()
			viewModel.onAsync(HomeState::featuredAds) {
				featuredAdsAdapter.submitList(it)
			}

			val menusAdapter = MenusAdapter()
			menus.apply {
				adapter = menusAdapter
				offscreenPageLimit = 2
			}
			viewModel.onAsync(HomeState::menus) {
				Log.d(TAG, "menus arrived")
				TabLayoutMediator(menuTabs, menus) { tab, pos ->
					Log.d(TAG, "assigning ${it[pos].title} tab")
					tab.text = it[pos].title
				}
				menusAdapter.submitList(it)
			}

			viewModel.onAsync(HomeState::cart) {
				Log.d(TAG, "cart has ${it.items.size} items")
			}

			fabOpenCart.apply {
				setOnClickListener { v ->
					Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
						.setAction("Action", null)
						.apply {
							ViewCompat.setOnApplyWindowInsetsListener(view) { _, _ ->
								setOnApplyWindowInsetsListener(null)
								WindowInsetsCompat.CONSUMED
							}
						}
						.show()
				}
			}
		}
		return binding.root
	}

	override fun invalidate() {
		withState(viewModel) { state ->
			Log.d(TAG, "invalidate: ")
		}
	}
}