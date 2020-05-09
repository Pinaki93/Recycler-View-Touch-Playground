package dev.pinaki.recyclerviewswipe.ui.adapter.diff

import androidx.recyclerview.widget.DiffUtil
import dev.pinaki.recyclerviewswipe.ui.adapter.Item

class ItemDiffUtilCallback(val oldList: List<Item>, val newList: List<Item>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].item == newList[newItemPosition].item
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].item == newList[newItemPosition].item
    }
}