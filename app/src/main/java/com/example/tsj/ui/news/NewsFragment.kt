package com.example.tsj.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.adapters.NewsAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tsj.MainActivity

class NewsFragment : Fragment() {

    private lateinit var fAdapter: NewsAdapter
    private lateinit var recyclerViewF: RecyclerView
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news, container, false)

        recyclerViewF = root.findViewById(R.id.recyclerViewFile)
        getRecyclerView()
        MainActivity.alert.show()
        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar?.show()

        return root
    }

    private fun getRecyclerView() {
        fAdapter = NewsAdapter()
        recyclerViewF.apply {
            adapter = fAdapter
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.news().observe(this, Observer { list->
            fAdapter.setList(list)
            MainActivity.alert.hide()
        })
    }
}