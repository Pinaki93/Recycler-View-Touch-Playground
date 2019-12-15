package dev.pinaki.recyclerviewswipe.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dev.pinaki.recyclerviewswipe.R
import dev.pinaki.recyclerviewswipe.callback.OnDragListener
import dev.pinaki.recyclerviewswipe.callback.OnSwipeCallback
import dev.pinaki.recyclerviewswipe.data.items
import dev.pinaki.recyclerviewswipe.ui.adapter.ItemAdapter
import dev.pinaki.recyclerviewswipe.ui.adapter.swipecallback.UpDownDragCallback

class MainActivity : AppCompatActivity(),
    OnSwipeCallback, OnDragListener {

    private lateinit var itemsRecyclerView: RecyclerView
    private lateinit var parent: View

    private lateinit var itemAdapter: ItemAdapter

    ////////////////////////////////////////////////////////////////////////////
    // Activity Callbacks
    ////////////////////////////////////////////////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parent = findViewById(R.id.parent)
        itemsRecyclerView = findViewById(R.id.rv_items)
        itemsRecyclerView.layoutManager = LinearLayoutManager(this)

        itemAdapter = ItemAdapter()

        itemAdapter.items = items
        itemsRecyclerView.adapter = itemAdapter

        initializeTouchHelper()
    }

    ////////////////////////////////////////////////////////////////////////////
    // Callbacks
    ////////////////////////////////////////////////////////////////////////////
    override fun onSwipeLeft(position: Int) {
        val deletedItem = itemAdapter.deleteItemAt(position)

        Snackbar.make(parent, getString(R.string.msg_item_deleted), Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.lbl_undo)) {
                itemAdapter.addItemAt(deletedItem, position)
            }.show()
    }

    override fun onSwipeRight(position: Int) {
        val archivedItem = itemAdapter.deleteItemAt(position)

        Snackbar.make(parent, getString(R.string.msg_item_archived), Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.lbl_undo)) {
                itemAdapter.addItemAt(archivedItem, position)
            }.show()
    }

    override fun onDrag(initialPosition: Int, finalPosition: Int) {
        itemAdapter.moveItem(initialPosition, finalPosition)
    }

    override fun onSelectedChanged(position: Int) {
        //TODO: Highlight selected item
    }

    override fun onClear(position: Int) {
        //TODO: Clear highlight
    }

    ////////////////////////////////////////////////////////////////////////////
    // Private Util Methods
    ////////////////////////////////////////////////////////////////////////////
    private fun initializeTouchHelper() {
        //TODO: Later on create a choice menu to select between different touch actions
//        val touchHelper = ItemTouchHelper(
//            LeftRightFullSwipeCallback(
//                this, this,
//                R.drawable.ic_delete_white_24dp,
//                Color.parseColor("#D1495B"),
//                R.drawable.ic_archive_white_24dp,
//                Color.parseColor("#F4E285")
//            )
//        )

        val touchHelper = ItemTouchHelper(
            UpDownDragCallback(this)
        )

        touchHelper.attachToRecyclerView(itemsRecyclerView)
    }
}
