package com.dindintest.util

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import com.dindintest.R

@BindingAdapter("isVisible")
fun setVisible(v: View, visible: Boolean) {
	v.isVisible = visible
}

@BindingAdapter("imgUrl")
fun setImage(iv: ImageView, imgUrl: String) {
	val cornerRadius = iv.resources.getDimensionPixelSize(R.dimen.menu_item_corner_radius).toFloat()
	iv.load(imgUrl) {
		crossfade(true)
	}
}

@BindingAdapter("bgImgUrl")
fun setBackground(v: View, imgUrl: String) {
	val request = ImageRequest.Builder(v.context)
		.data(imgUrl)
		.target { drawable ->
			v.background = drawable
		}
		.crossfade(true)
		.build()
	v.context.imageLoader.enqueue(request)
}

