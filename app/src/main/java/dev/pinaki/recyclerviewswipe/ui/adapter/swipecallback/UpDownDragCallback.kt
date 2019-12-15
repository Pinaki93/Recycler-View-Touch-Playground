package dev.pinaki.recyclerviewswipe.ui.adapter.swipecallback

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import dev.pinaki.recyclerviewswipe.callback.OnDragListener

class UpDownDragCallback(
    val onDragListener: OnDragListener? = null
) : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.UP or ItemTouchHelper.DOWN,
    0
) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val initialPosition = viewHolder.adapterPosition
        val finalPosition = target.adapterPosition

        onDragListener?.onDrag(initialPosition, finalPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // Stub
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        viewHolder?.let {
            onDragListener?.onSelectedChanged(viewHolder.adapterPosition)
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        onDragListener?.onClear(viewHolder.adapterPosition)
    }
}