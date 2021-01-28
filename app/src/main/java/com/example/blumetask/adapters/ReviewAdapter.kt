package com.example.blumetask.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blumetask.R
import com.example.blumetask.activities.ItemDetailsActivity

class ReviewAdapter(var context: Context?) : RecyclerView.Adapter<ReviewAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.review_item, parent, false
        )
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int=10

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


}