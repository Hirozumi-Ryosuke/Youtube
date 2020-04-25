package com.youtube.player.base.widgets

import android.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat


class SimpleDividerItemDecoration(context: Context?) : RecyclerView.ItemDecoration() {
    private val mDivider: Drawable?
    fun onDrawOver(c: Canvas?, parent: RecyclerView, state: RecyclerView.State?) {
        val left: Int = parent.getPaddingLeft()
        val right: Int = parent.getWidth() - parent.getPaddingRight()
        val childCount: Int = parent.getChildCount()
        for (i in 0 until childCount) {
            val child: View = parent.getChildAt(i)
            val params: RecyclerView.LayoutParams =
                child.getLayoutParams() as RecyclerView.LayoutParams
            val top: Int = child.getBottom() + params.bottomMargin
            val bottom = top + mDivider!!.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }

    init {
        mDivider = ContextCompat.getDrawable(context, R.drawable.line_divider)
    }
}