package com.example.tsj.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.adapters.news.NewsAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.tsj.MainActivity
import com.example.tsj.adapters.news.NewsOnItemClickListener
import com.example.tsj.service.model.news.NewsModel

class NewsFragment : Fragment(), NewsOnItemClickListener {


    private lateinit var newsAdapter: NewsAdapter
    private lateinit var news_rv: RecyclerView
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news, container, false)

        news_rv = root.findViewById(R.id.recyclerViewFile)
        MainActivity.alert.show()
        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar?.show()
        getRecyclerView()
        return root
    }

    private fun getRecyclerView() {
        viewModel.news().observe(this, Observer { list ->
            newsAdapter = NewsAdapter(list, this)
            news_rv.apply {
                adapter = newsAdapter
                MainActivity.alert.hide() }
        })
    }


    override fun newsItemOnClicked(newsModel: NewsModel) {
        val bundle = Bundle()
        bundle.putInt("newsId", newsModel.id)
        findNavController().navigate(R.id.navigation_news_detail, bundle)
    }

}
