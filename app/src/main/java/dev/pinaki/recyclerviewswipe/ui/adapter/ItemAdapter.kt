package dev.pinaki.recyclerviewswipe.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.pinaki.recyclerviewswipe.R
import dev.pinaki.recyclerviewswipe.databinding.ItemViewBinding
import dev.pinaki.recyclerviewswipe.ui.adapter.diff.ItemDiffUtilCallback

data class Item(
    val item: String
)

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    var items: MutableList<Item> = ArrayList()
        set(items) {
            field.clear()
            field.addAll(items)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setItem(items[position])
    }

    fun deleteItemAt(position: Int): Item {
        val oldList = items

        val newList = ArrayList<Item>()
        newList.addAll(oldList)

        val removedItem = newList[position]
        newList.removeAt(position)

        val diff = DiffUtil.calculateDiff(ItemDiffUtilCallback(oldList, newList))
        diff.dispatchUpdatesTo(this)

        return removedItem
    }

    fun addItemAt(item: Item, position: Int) {
        val oldList = items

        val newList = ArrayList<Item>()
        newList.addAll(oldList)
        newList.add(position, item)
        val diff = DiffUtil.calculateDiff(ItemDiffUtilCallback(oldList, newList))
        diff.dispatchUpdatesTo(this)
    }

    inner class ItemViewHolder(
        private val binding: ItemViewBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun setItem(item: Item) {
            binding.tvItemName.text = item.item
        }
    }
}