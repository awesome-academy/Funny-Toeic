package com.sun.funnytoeic.ui.base

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sun.funnytoeic.utils.replaceAll

abstract class UpdatableAdapter<T, VH : UpdatableAdapter.ViewHolder<T>>(
    private val items: MutableList<T>
) : RecyclerView.Adapter<VH>() {

    fun updateData(data: List<T>) =
        DiffUtil.calculateDiff(Callback(items, data)).run {
            items.replaceAll(data)
            dispatchUpdatesTo(this@UpdatableAdapter)
        }

    abstract class ViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bindData(item: T)
    }

    class Callback<T>(private val oldData: List<T>, private val newData: List<T>) :
        DiffUtil.Callback() {

        override fun getOldListSize() = oldData.size

        override fun getNewListSize() = newData.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldData[oldItemPosition] == newData[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            areItemsTheSame(oldItemPosition, newItemPosition)
    }
}
