package dev.pinaki.recyclerviewswipe.callback

interface OnSwipeCallback {
    fun onSwipeLeft(position: Int)

    fun onSwipeRight(position: Int)
}