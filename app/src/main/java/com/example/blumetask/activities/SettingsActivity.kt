package com.example.blumetask.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.blumetask.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        my_order.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            my_order ->{
                val intent = Intent(this,MyOrderActivity::class.java)
                startActivity(intent)
            }

        }
    }
}