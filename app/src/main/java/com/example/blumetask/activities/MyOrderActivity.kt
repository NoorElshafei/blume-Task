package com.example.blumetask.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blumetask.R
import com.example.blumetask.adapters.CartsAdapter
import com.example.blumetask.adapters.ReviewAdapter
import kotlinx.android.synthetic.main.activity_item_details.*
import kotlinx.android.synthetic.main.activity_my_order.*

class MyOrderActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_order)
        check_out.setOnClickListener(this)

        val linearLayoutManager: LinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        carts_recycler.layoutManager = linearLayoutManager
        val adapter = CartsAdapter(this)
        carts_recycler.adapter = adapter
    }

    override fun onClick(view: View?) {
        when(view){
            check_out ->{
                val intent = Intent(this,CheckoutActivity::class.java)
                startActivity(intent)
            }
        }
    }
}