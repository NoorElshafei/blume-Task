package com.example.blumetask.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.blumetask.R
import com.example.blumetask.adapters.CartsAdapter
import com.example.blumetask.database.CartRoomDataBase
import com.example.blumetask.database.RoomDao
import com.example.blumetask.model.CartRoomModel
import com.example.blumetask.model.Request
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : AppCompatActivity(), View.OnClickListener,
    CartsAdapter.TotalCartInterFace {
    private lateinit var userDao: RoomDao
    private lateinit var requests: DatabaseReference
    private lateinit var database: FirebaseDatabase
    var total: Int = 0
    lateinit var cartsList: List<CartRoomModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        place_order_button.setOnClickListener(this)
        back.setOnClickListener(this)


        //Firabase coonection
        database = FirebaseDatabase.getInstance()
        requests = database.getReference("Requests")

        total = 0
        val db = Room.databaseBuilder(
            this,
            CartRoomDataBase::class.java, "CartRoomModel"
        ).allowMainThreadQueries().build()
        userDao = db.userDao()
        cartsList = userDao.getAll()


        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        items_recyclerView.layoutManager = linearLayoutManager
        val adapter = CartsAdapter(this, cartsList, this)
        items_recyclerView.adapter = adapter
    }

    override fun onClick(view: View?) {
        when (view) {
            place_order_button -> {
                val request = Request(
                    "48 mostafa elnahas, nasr city, cairo",
                    cartsList, "nooor", "cash",
                    "unpaid", "0102222121", "0",
                    total.toString()
                )

                requests.child(System.currentTimeMillis().toString()).setValue(request)
                    .addOnCompleteListener {
                        userDao.deleteAll()
                       startActivity(Intent(this,FinishOrderActivity::class.java))
                    }.addOnFailureListener { fail ->
                        Toast.makeText(this, fail.message, Toast.LENGTH_SHORT)
                            .show()
                    }


            }
            back->{
                onBackPressed()
            }

        }
    }

    override fun addToTatal(price: Int) {
        total += price
        total_price.text = total.toString() + " L.E"
    }

    override fun minusfromTatal(price: Int) {
        total -= price
        total_price.text = total.toString() + " L.E"
    }
}