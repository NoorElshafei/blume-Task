package com.example.blumetask.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blumetask.R
import com.example.blumetask.adapters.ItemAdapter
import com.example.blumetask.adapters.RecentlyViewedItemAdapter
import kotlinx.android.synthetic.main.fragment_blank.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class SearchFragment : Fragment() {
    lateinit var adapter: RecentlyViewedItemAdapter

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

        val layoutManager: LinearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        view.recently_recyclerView.layoutManager = layoutManager
        adapter = RecentlyViewedItemAdapter()
        view.recently_recyclerView.adapter = adapter
        return  view
    }

}