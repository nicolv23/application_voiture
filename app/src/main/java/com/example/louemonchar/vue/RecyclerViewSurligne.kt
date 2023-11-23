package com.example.louemonchar.vue

import android.graphics.Color
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewSurligne (private val recyclerView: RecyclerView) :
    RecyclerView.ItemDecoration() {

    init {
        recyclerView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
                val enfant = recyclerView.findChildViewUnder(event.x, event.y)
                surlignerModele(enfant)
            }
            false
        }
    }

    private fun surlignerModele(view: View?) {
        val couleurVert = Color.parseColor("#8CFF32")
        for (i in 0 until recyclerView.childCount) {
            val enfant = recyclerView.getChildAt(i)
            if (enfant == view) {
                enfant.setBackgroundColor(couleurVert)
            } else {
                enfant.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }
}