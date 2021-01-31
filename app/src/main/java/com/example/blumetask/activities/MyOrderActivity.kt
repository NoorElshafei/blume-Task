package com.example.blumetask.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.blumetask.R
import com.example.blumetask.adapters.CartsAdapter
import com.example.blumetask.database.CartRoomDataBase
import com.example.blumetask.model.CartRoomModel
import kotlinx.android.synthetic.main.activity_my_order.*

class MyOrderActivity : AppCompatActivity(), View.OnClickListener, CartsAdapter.TotalCartInterFace {
    private lateinit var cartsList: List<CartRoomModel>
    var total: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_order)
        total = 0
        check_out.setOnClickListener(this)
        back.setOnClickListener(this)
        val db = Room.databaseBuilder(
            this,
            CartRoomDataBase::class.java, "CartRoomModel"
        ).allowMainThreadQueries().build()
        val userDao = db.userDao()
         cartsList = userDao.getAll()


        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        carts_recycler.layoutManager = linearLayoutManager
        val adapter = CartsAdapter(this, cartsList, this)
        carts_recycler.adapter = adapter
    }

    override fun onClick(view: View?) {
        when (view) {
            check_out -> {
                if (cartsList.isEmpty()) {
                    Toast.makeText(this, "Please Add Item to the cart first", Toast.LENGTH_SHORT)
                        .show()

                } else {
                    val intent = Intent(this, CheckoutActivity::class.java)
                    startActivity(intent)
                }
            }
            back->{
                onBackPressed()
            }
        }
    }

    override fun addToTatal(price: Int) {
        total += price
        total_carts.text = total.toString() + " L.E"
    }

    override fun minusfromTatal(price: Int) {
        total -= price
        total_carts.text = total.toString() + " L.E"
    }
}