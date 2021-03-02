package com.dindintest.ui.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.airbnb.mvrx.fragmentViewModel
import com.dindintest.R
import com.dindintest.databinding.FragmentCheckoutBinding
import com.dindintest.ui.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator

class CheckoutFragment : BaseFragment() {

	private val viewModel: CheckoutViewModel by fragmentViewModel()
	private lateinit var binding: FragmentCheckoutBinding
	private val cartItemAdapter = CartItemAdapter {
		viewModel.removeFromCart(it)
	}
	private val cartSummaryAdapter = CartSummaryAdapter()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentCheckoutBinding.inflate(inflater, container, false).apply {

			toolbar.setupWithNavController(findNavController())

			val pagerAdapter = CheckoutPagerAdapter(cartItemAdapter, cartSummaryAdapter)
			pager.apply {
				adapter = pagerAdapter
				offscreenPageLimit = 1
			}
			TabLayoutMediator(tabs, pager) { tab, pos ->
				val label = when (pos) {
					0 -> R.string.cart
					1 -> R.string.orders
					else -> R.string.info
				}
				tab.setText(label)
			}.attach()

			fab.setOnClickListener {
				Toast.makeText(requireContext(), "Checked out", Toast.LENGTH_SHORT).show()
				viewModel.checkout()
			}

			viewModel.onAsync(CheckoutState::cart) {
				cartItemAdapter.submitList(it.items)
				cartSummaryAdapter.total = it.total
			}
			viewModel.onAsync(CheckoutState::removeItemRequest) {
				cartItemAdapter.submitList(it.items)
				cartSummaryAdapter.total = it.total
			}
			viewModel.onAsync(CheckoutState::checkoutRequest) {
				findNavController().navigateUp()
			}
		}
		return binding.root
	}

	override fun invalidate() {
		/*withState(viewModel) { state ->

		}*/
	}
}