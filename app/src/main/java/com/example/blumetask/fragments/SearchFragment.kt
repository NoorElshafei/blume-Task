package com.example.blumetask.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blumetask.R
import com.example.blumetask.adapters.ItemAdapter
import com.example.blumetask.adapters.RecentlyViewedItemAdapter
import com.example.blumetask.adapters.SearchProductAdapter
import com.example.blumetask.model.Products
import com.example.blumetask.util.anim_menu
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_blank.view.*


class SearchFragment : Fragment() {
    private lateinit var searchAdapter: FirebaseRecyclerAdapter<Products, ItemAdapter.ProductsViewHolder>
    private lateinit var database: FirebaseDatabase
    lateinit var adapter: RecentlyViewedItemAdapter
    var productList: DatabaseReference? = null


    companion object {
        fun newInstance() = SearchFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_blank, container, false)



        view.search_action1.setOnClickListener {

          performSearch()
        }
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        view.recently_recyclerView.layoutManager = layoutManager
        adapter = RecentlyViewedItemAdapter()
        view.recently_recyclerView.adapter = adapter
        return  view
    }

    private fun performSearch() {
        view!!.search_recycler.visibility=View.VISIBLE
        view!!.layout_recent.visibility =View.GONE


        val daysManager = FlexboxLayoutManager(context)
        daysManager.flexDirection = FlexDirection.ROW
        daysManager.justifyContent = JustifyContent.SPACE_BETWEEN
        daysManager.flexWrap = FlexWrap.WRAP
        view!!.search_recycler.layoutManager = daysManager
        val adapterS = SearchProductAdapter(context,productList,view!!.search_editText.text.toString())
        view!!.search_recycler.adapter = adapterS


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        //firebase
        database = FirebaseDatabase.getInstance()
        productList = database.getReference("Products")


        view!!.search_editText.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                return@OnEditorActionListener true
            }
            false
        })


    }


    override fun onStart() {
        super.onStart()
        activity!!.anim_menu(
            activity!!.findViewById(R.id.search),
            R.drawable.ic_search,
            R.drawable.ic_search,
            true
        )
    }

    override fun onStop() {
        super.onStop()
        activity!!.anim_menu(
            activity!!.findViewById(R.id.search),
            R.drawable.ic_search,
            R.drawable.ic_search,
            false
        )
    }


}