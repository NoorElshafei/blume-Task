package com.example.blumetask.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blumetask.R

class RecentlyViewedItemAdapter : RecyclerView.Adapter<RecentlyViewedItemAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.recently_viewed_item, parent, false
        )
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int=10

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


}