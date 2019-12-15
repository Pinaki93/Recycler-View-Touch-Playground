package dev.pinaki.recyclerviewswipe.callback

interface OnDragListener {
    fun onDrag(initialPosition: Int, finalPosition: Int)

    fun onSelectedChanged(position: Int)

    fun onClear(position: Int)
}