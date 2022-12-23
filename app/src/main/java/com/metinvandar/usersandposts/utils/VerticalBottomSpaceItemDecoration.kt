package com.metinvandar.usersandposts.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalBottomSpaceItemDecoration(context: Context, vertical: Int, private val ignoreLastIndex: Boolean = false) : RecyclerView.ItemDecoration() {

    private val verticalSpace: Int = (
        vertical * context
            .resources
            .displayMetrics.density
        ).toInt()

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        when (ignoreLastIndex) {
            true -> outRect.bottom = verticalSpace
            false -> {
                if (parent.getChildAdapterPosition(view) != parent.adapter!!.itemCount - 1) {
                    outRect.bottom = verticalSpace
                }
            }
        }
    }
}
