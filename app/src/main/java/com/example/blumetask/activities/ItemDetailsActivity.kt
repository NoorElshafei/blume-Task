package com.example.blumetask.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.blumetask.R
import com.example.blumetask.adapters.ReviewAdapter
import com.example.blumetask.database.CartRoomDataBase
import com.example.blumetask.database.RoomDao
import com.example.blumetask.model.CartRoomModel
import com.example.blumetask.model.Products
import kotlinx.android.synthetic.main.activity_item_details.*
import java.util.*

class ItemDetailsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var userDao1: RoomDao
    var db: CartRoomDataBase? = null

    lateinit var productItem: Products
    lateinit var productId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)
        db = Room.databaseBuilder(
            this,
            CartRoomDataBase::class.java, "CartRoomModel"
        ).allowMainThreadQueries().build()
        userDao1 = db!!.userDao()


        val sizeOfCart = userDao1.getAll().size

        if(sizeOfCart>=0){
            cart_number.visibility= View.VISIBLE
            cart_number.text = sizeOfCart.toString()
        }else{
            cart_number.visibility= View.GONE

        }



        reviews.setOnClickListener(this)
        details.setOnClickListener(this)
        add_to_cart.setOnClickListener(this)
        back.setOnClickListener(this)
        cart.setOnClickListener(this)
        cancel_button.setOnClickListener(this)

        if (intent.getStringExtra("PRODUCT_id") != null) {
            productId = intent.getStringExtra("PRODUCT_id")!!
        }
        if (intent.getParcelableExtra<Products>("PRODUCT_ITEM") != null) {
            productItem = intent.getParcelableExtra("PRODUCT_ITEM")!!
            setUi(productItem)
        }

        val linearLayoutManager: LinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        review_recycler.layoutManager = linearLayoutManager
        val adapter = ReviewAdapter(this)
        review_recycler.adapter = adapter

    }

    private fun setUi(productItem: Products) {
        item_name1.text = productItem.name
        condition.text = productItem.condition
        price_item.text = productItem.price + " L.E"
        name_item.text = productItem.name
        material.text = productItem.material

    }

    override fun onClick(view: View?) {
        when (view) {
            reviews -> {
                details_layout.visibility = View.GONE
                review_recycler.visibility = View.VISIBLE
            }
            details -> {
                details_layout.visibility = View.VISIBLE
                review_recycler.visibility = View.GONE
            }
            add_to_cart -> {
                val userDao = db!!.userDao()
                userDao.insertAll(
                    CartRoomModel(
                        productId,
                        productItem.name,
                        productItem.price,
                        "1"
                    )
                )
                Toast.makeText(this, "Added To Cart", Toast.LENGTH_SHORT).show()
                val sizeOfCarts =  userDao1.getAll().size
                cart_number.text = sizeOfCarts.toString()

            }
            back -> {
                onBackPressed()
            }
            cancel_button -> {
                onBackPressed()
            }
            cart -> {
                startActivity(Intent(this, MyOrderActivity::class.java))
            }

        }
    }
}