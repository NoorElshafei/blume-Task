package com.example.blumetask.adapters

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.blumetask.R
import com.example.blumetask.activities.ItemDetailsActivity
import com.example.blumetask.model.Products
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.item_list.view.*

class SearchProductAdapter(
    var context: Context?,
    var ref: DatabaseReference?,
    var text: String?
) : FirebaseRecyclerAdapter<Products, SearchProductAdapter.SearchViewHolder>(
    Products::
    class.java, R.layout.item_list, SearchViewHolder::
    class.java, ref!!.orderByChild("name").startAt(text)
        .endAt(text + "\uf8ff")
) {


    override fun populateViewHolder(
        holder: SearchViewHolder?,
        product: Products?,
        position: Int
    ) {
        holder!!.itemView.name_item.text = product!!.name
        holder.itemView.item_price.text = product.price + " L.E"


        holder.itemView.setOnClickListener {
            val intent = Intent(context, ItemDetailsActivity::class.java)
            intent.putExtra("PRODUCT_ITEM", product)
            intent.putExtra("PRODUCT_id", getRef(position).key)
            context!!.startActivity(intent)
        }
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}