package com.example.blumetask

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blumetask.adapters.ItemAdapter
import com.example.blumetask.fragments.HomeFragment
import com.example.blumetask.fragments.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search.setOnClickListener(this)
        home.setOnClickListener(this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment.newInstance(), null).commit()


    }

    override fun onClick(view: View?) {
        when(view){
            search ->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SearchFragment.newInstance(), null).commit()
            }
            home ->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment.newInstance(), null).commit()
            }
        }
    }
}