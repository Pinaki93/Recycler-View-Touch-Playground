package dev.pinaki.recyclerviewswipe.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.pinaki.recyclerviewswipe.R
import dev.pinaki.recyclerviewswipe.databinding.ItemViewBinding
import java.util.*
import kotlin.collections.ArrayList

data class Item(
    val item: String,
    var highlighted: Boolean = false
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
        val removedItem = items[position]

        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size)

        return removedItem
    }

    fun addItemAt(item: Item, position: Int) {
        items.add(position, item)
        notifyItemInserted(position)
        notifyItemRangeChanged(position, items.size)
    }

    fun moveItem(initialPosition: Int, finalPosition: Int) {
        Collections.swap(items, initialPosition, finalPosition)
        notifyItemMoved(initialPosition, finalPosition)
    }

    inner class ItemViewHolder(
        private val binding: ItemViewBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun setItem(item: Item) {
            binding.tvItemName.text = item.item
            itemView.setBackgroundColor(
                if (item.highlighted) Color.parseColor("#ff0000") else Color.parseColor(
                    "#ffffff"
                )
            )
        }
    }
}