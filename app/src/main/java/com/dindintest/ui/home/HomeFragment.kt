package com.dindintest.ui.home

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
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

			val featuredAdsAdapter = FeaturedAdsAdapter {
				Toast.makeText(requireContext(), "Ad id $it was clicked", Toast.LENGTH_SHORT).show()
			}
			featuredAds.apply {
				adapter = featuredAdsAdapter
				offscreenPageLimit = 2
			}
			TabLayoutMediator(featuredAdDots, featuredAds) { _, _ -> }.attach()

			val menusAdapter = MenusAdapter {
				Log.d(TAG, "bought item id $it")
				viewModel.addToCart(it)
			}
			menus.apply {
				adapter = menusAdapter
				offscreenPageLimit = 2
			}
			TabLayoutMediator(menuTabs, menus) { tab, pos ->
				val menu = menusAdapter.currentList[pos]
				tab.text = menu.title
			}.attach()

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

			viewModel.onAsync(HomeState::addOrUpdateItemRequest) {
				if (it == 0) {
					badge.isVisible = false
				} else {
					badge.apply {
						text = "$it"
						isVisible = true
						val interpolator = BounceInterpolator()
						val scaleX =
							ObjectAnimator.ofFloat(this, "scaleX", 1.5f, 0.8f, 1.2f, 1f).apply {
								setInterpolator(interpolator)
							}
						val scaleY =
							ObjectAnimator.ofFloat(this, "scaleY", 1.5f, 0.8f, 1.2f, 1f).apply {
								setInterpolator(interpolator)
							}
						AnimatorSet().apply {
							playTogether(scaleX, scaleY)
							start()
						}
					}
				}
			}
		}
		return binding.root
	}

	override fun invalidate() {
		withState(viewModel) { state ->
			binding.state = state
		}
	}
}