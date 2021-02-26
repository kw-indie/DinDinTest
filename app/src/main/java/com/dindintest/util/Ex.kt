package com.dindintest.util

inline fun <T> Iterable<T>.sumOf(selector: (T) -> Float): Float {
	var sum: Float = 0f
	for (t in this) {
		sum += selector(t)
	}
	return sum
}