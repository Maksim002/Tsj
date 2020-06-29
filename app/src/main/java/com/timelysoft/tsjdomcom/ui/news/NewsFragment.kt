package com.timelysoft.tsjdomcom.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.news.NewsAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import com.timelysoft.tsjdomcom.adapters.news.NewsOnItemClickListener
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.news.NewsModel

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
        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar?.show()
        getRecyclerView()
        return root
    }

    private fun getRecyclerView() {
        MainActivity.alert.show()
        viewModel.news().observe(viewLifecycleOwner, Observer { result ->
            val msg = result.msg
            val data = result.data
            MainActivity.alert.hide()
            when(result.status){
                Status.SUCCESS ->{
                    newsAdapter = NewsAdapter(data!!, this)
                    news_rv.apply {
                        adapter = newsAdapter
                    }
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })
    }


    override fun newsItemOnClicked(newsModel: NewsModel) {
        val bundle = Bundle()
        bundle.putInt("newsId", newsModel.id)
        findNavController().navigate(R.id.navigation_news_detail, bundle)
    }

}
