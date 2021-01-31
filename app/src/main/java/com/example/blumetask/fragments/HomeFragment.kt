package com.example.blumetask.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.blumetask.R
import com.example.blumetask.activities.MyOrderActivity
import com.example.blumetask.activities.SettingsActivity
import com.example.blumetask.adapters.ItemAdapter
import com.example.blumetask.adapters.SearchProductAdapter
import com.example.blumetask.database.CartRoomDataBase
import com.example.blumetask.database.RoomDao
import com.example.blumetask.model.Products
import com.example.blumetask.util.anim_menu
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class HomeFragment : Fragment(),View.OnClickListener {
    private lateinit var userDao1: RoomDao

    private lateinit var db: CartRoomDataBase
    lateinit var adapter: ItemAdapter
    lateinit var listProducts:ArrayList<Products>

    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference

    companion object {
        fun newInstance() = HomeFragment()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        db = Room.databaseBuilder(
            context!!.applicationContext,
            CartRoomDataBase::class.java, "CartRoomModel"
        ).allowMainThreadQueries().build()
        userDao1 = db!!.userDao()


        view!!.recycler_view.adapter = adapter


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        listProducts = ArrayList()

        database = FirebaseDatabase.getInstance()
        ref = database.getReference("Products")

        val daysManager = FlexboxLayoutManager(context)
        daysManager.flexDirection = FlexDirection.ROW
        daysManager.justifyContent = JustifyContent.SPACE_BETWEEN
        daysManager.flexWrap = FlexWrap.WRAP
        view.recycler_view.layoutManager = daysManager
        adapter = ItemAdapter(context,ref)

        view.option.setOnClickListener(this)
        view.background_menu.setOnClickListener(this)
        view.setting.setOnClickListener(this)
        view.about.setOnClickListener(this)
        view.rate_us.setOnClickListener(this)
        view.log_out.setOnClickListener(this)
        view.menu.setOnClickListener(this)
        view.search_action.setOnClickListener(this)
        view.cart_home.setOnClickListener(this)


        view.search_editText.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                return@OnEditorActionListener true
            }
            false
        })

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
            view!!.search_action ->{
              performSearch()
            }
            view!!.cart_home ->{
               startActivity(Intent(context,MyOrderActivity::class.java))
            }
        }
    }

    private fun performSearch() {
        val daysManager = FlexboxLayoutManager(context)
        daysManager.flexDirection = FlexDirection.ROW
        daysManager.justifyContent = JustifyContent.SPACE_BETWEEN
        daysManager.flexWrap = FlexWrap.WRAP
        view!!.recycler_view.layoutManager = daysManager
        val adapterS = SearchProductAdapter(context,ref,view!!.search_editText.text.toString())
        view!!.recycler_view.adapter = adapterS
    }

    override fun onStart() {
        super.onStart()
        activity!!.anim_menu(
            activity!!.findViewById(R.id.home),
            R.drawable.ic_home,
            R.drawable.ic_home,
            true
        )


        val sizeOfCart = userDao1.getAll().size

        if(sizeOfCart>=0){
            view!!.cart_number_homr.visibility= View.VISIBLE
            view!!.cart_number_homr.text = sizeOfCart.toString()
        }else{
            view!!.cart_number_homr.visibility= View.GONE

        }


    }

    override fun onStop() {
        super.onStop()
        activity!!.anim_menu(
            activity!!.findViewById(R.id.home),
            R.drawable.ic_home,
            R.drawable.ic_home,
            false
        )
    }

}