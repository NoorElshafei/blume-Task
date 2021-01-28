package com.example.blumetask.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blumetask.R
import com.example.blumetask.adapters.ItemAdapter
import com.example.blumetask.adapters.ReviewAdapter
import kotlinx.android.synthetic.main.activity_item_details.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class ItemDetailsActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)

        reviews.setOnClickListener(this)
        details.setOnClickListener(this)

        val linearLayoutManager: LinearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        review_recycler.layoutManager = linearLayoutManager
        val adapter = ReviewAdapter(this)
        review_recycler.adapter = adapter

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

        }
    }
}