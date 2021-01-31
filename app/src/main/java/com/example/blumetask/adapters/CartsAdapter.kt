package com.example.blumetask.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.blumetask.R
import com.example.blumetask.database.CartRoomDataBase
import com.example.blumetask.model.CartRoomModel
import kotlinx.android.synthetic.main.item_cart.view.*


class CartsAdapter(
    var context: Context?,
    var cartsList: List<CartRoomModel>,
    var totalCarts:TotalCartInterFace
) : RecyclerView.Adapter<CartsAdapter.ItemViewHolder>() {
    var db:CartRoomDataBase?= null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        db = Room.databaseBuilder(
           context!!,
            CartRoomDataBase::class.java, "CartRoomModel"
        ).allowMainThreadQueries().build()
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_cart, parent, false
        )
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = cartsList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val price:Int = cartsList[position].price!!.toInt()
        val quantity:Int = cartsList[position].quantity!!.toInt()

        holder.itemView.name_cart.text = cartsList[position].name
        holder.itemView.price_item.text = (price* quantity).toString()+" L.E"
        holder.itemView.number_button.number = cartsList[position].quantity

        totalCarts.addToTatal(price* quantity)


        holder.itemView.number_button.setOnValueChangeListener { view, oldValue, newValue ->
            val totalPriceWithQuantity = cartsList[position].price!!.toInt() * newValue
            holder.itemView.price_item.text = totalPriceWithQuantity.toString()+" L.E"
            if(oldValue<newValue) {
                totalCarts.addToTatal(cartsList[position].price!!.toInt())
            }else if(oldValue>newValue){
                totalCarts.minusfromTatal(cartsList[position].price!!.toInt())
            }
            val userDao = db!!.userDao()
            userDao.updateCart(cartsList[position].id!!,newValue.toString())
        }

    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    interface TotalCartInterFace {
        fun addToTatal(price: Int)
        fun minusfromTatal(price: Int)
    }
}
