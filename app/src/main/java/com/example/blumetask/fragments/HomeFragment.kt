package com.example.blumetask.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blumetask.R
import com.example.blumetask.activities.SettingsActivity
import com.example.blumetask.adapters.ItemAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment(),View.OnClickListener {
    lateinit var adapter: ItemAdapter
    companion object {
        fun newInstance() = HomeFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val gridLayoutManager: GridLayoutManager = GridLayoutManager(context, 2)
        val daysManager = FlexboxLayoutManager(context)
        daysManager.flexDirection = FlexDirection.ROW
        daysManager.justifyContent = JustifyContent.SPACE_BETWEEN
        daysManager.flexWrap = FlexWrap.WRAP
        view.recycler_view.layoutManager = daysManager
        adapter = ItemAdapter(context)
        view.recycler_view.adapter = adapter

        view.option.setOnClickListener(this)
        view.background_menu.setOnClickListener(this)
        view.setting.setOnClickListener(this)
        view.about.setOnClickListener(this)
        view.rate_us.setOnClickListener(this)
        view.log_out.setOnClickListener(this)
        view.menu.setOnClickListener(this)

        return view
    }

    override fun onClick(view1: View?) {
        when(view1){
            view!!.option ->{
                view!!.menu.visibility = View.VISIBLE
                view!!.background_menu.visibility = View.VISIBLE
            }
            view!!.background_menu ->{
                view!!.menu.visibility = View.GONE
                view!!.background_menu.visibility = View.GONE
            }
            view!!.setting ->{
                view!!.menu.visibility = View.GONE
                view!!.background_menu.visibility = View.GONE
                val intent = Intent(context,SettingsActivity::class.java)
                startActivity(intent)
            }
            view!!.rate_us ->{
                Toast.makeText(context,"rate us",Toast.LENGTH_SHORT).show()
            }
            view!!.about ->{
                Toast.makeText(context,"about",Toast.LENGTH_SHORT).show()
            }
            view!!.menu ->{
                Toast.makeText(context,"about",Toast.LENGTH_SHORT).show()
            }
            view!!.log_out ->{
                Toast.makeText(context,"log out",Toast.LENGTH_SHORT).show()
            }
        }
    }


}