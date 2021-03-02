package com.dindintest.ui.home

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.dindintest.R
import com.dindintest.databinding.FragmentHomeBinding
import com.dindintest.ui.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment() {

	private val viewModel: HomeViewModel by fragmentViewModel()
	private lateinit var binding: FragmentHomeBinding

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentHomeBinding.inflate(inflater, container, false).apply {

			val featuredAdsAdapter = FeaturedAdsAdapter {
				Toast.makeText(requireContext(), "Ad id $it was clicked", Toast.LENGTH_SHORT).show()
			}
			featuredAds.apply {
				adapter = featuredAdsAdapter
				offscreenPageLimit = 2
			}
			TabLayoutMediator(featuredAdDots, featuredAds) { _, _ -> }.attach()

			val menusAdapter = MenusAdapter {
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

			fab.setOnClickListener {
				findNavController().navigate(R.id.action_open_checkout)
			}

			val anim = createBadgeAnim(badge)
			viewModel.onAsync(HomeState::addOrUpdateItemRequest) {
				if (it == 0) {
					badge.isVisible = false
				} else {
					anim.cancel()
					badge.apply {
						text = "$it"
						isVisible = true
					}
					anim.start()
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

	private fun createBadgeAnim(view: View): AnimatorSet {
		val interpolator = BounceInterpolator()
		val scaleX =
			ObjectAnimator.ofFloat(view, "scaleX", 1.5f, 1f).apply {
				setInterpolator(interpolator)
			}
		val scaleY =
			ObjectAnimator.ofFloat(view, "scaleY", 1.5f, 1f).apply {
				setInterpolator(interpolator)
			}
		return AnimatorSet().apply {
			playTogether(scaleX, scaleY)
		}
	}
}