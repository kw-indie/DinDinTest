package com.dindintest.util

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.airbnb.mvrx.Async
import com.dindintest.R

@BindingAdapter("visibleIf")
fun visibleIf(v: View, visible: Boolean) {
	v.isVisible = visible
}

@BindingAdapter("imgUrl")
fun setImage(iv: ImageView, imgUrl: String) {
	val cornerRadius = iv.resources.getDimensionPixelSize(R.dimen.menu_item_corner_radius).toFloat()
	iv.load(imgUrl) {
		crossfade(true)
	}
}

@BindingAdapter("asyncList")
fun <T> setListAdapterData(scrollView: ViewGroup, list: Async<List<T>>?) {
	@Suppress("UNCHECKED_CAST")
	val adapter = when (scrollView) {
		is RecyclerView -> scrollView.adapter
		is ViewPager2 -> scrollView.adapter
		else -> return
	} as ListAdapter<T, *>
	adapter.submitList(list?.invoke() ?: emptyList())
}

@BindingAdapter("list")
fun <T> setListAdapterData(scrollView: ViewGroup, list: List<T>?) {
	@Suppress("UNCHECKED_CAST")
	val adapter = when (scrollView) {
		is RecyclerView -> scrollView.adapter
		is ViewPager2 -> scrollView.adapter
		else -> return
	} as ListAdapter<T, *>
	adapter.submitList(list ?: emptyList())
}

