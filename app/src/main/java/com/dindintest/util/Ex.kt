package com.dindintest.util

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton

inline fun <T> Iterable<T>.sumOf(selector: (T) -> Float): Float {
	var sum: Float = 0f
	for (t in this) {
		sum += selector(t)
	}
	return sum
}

fun BadgeDrawable.attachTo(anchor: FloatingActionButton) {
	/*val observer = ViewTreeObserver.OnGlobalLayoutListener {
		bounds =
	}*/
	anchor.viewTreeObserver.addOnGlobalLayoutListener(object :
		ViewTreeObserver.OnGlobalLayoutListener {
		@SuppressLint("UnsafeExperimentalUsageError")
		override fun onGlobalLayout() {
			BadgeUtils.attachBadgeDrawable(this@attachTo, anchor)
			verticalOffset = (bounds.bottom / 6f).toInt()
			horizontalOffset = (bounds.right / 6f).toInt()
			anchor.viewTreeObserver.removeOnGlobalLayoutListener(this)
		}
	})
}

val View.indexInParent get() = (parent as ViewGroup).indexOfChild(this)