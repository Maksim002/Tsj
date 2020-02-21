package com.example.tsj.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.R
import com.example.tsj.adapters.NewsAdapter
import com.example.tsj.model.NewsModel
import androidx.appcompat.app.AppCompatActivity
class NewsFragment : Fragment() {

    private lateinit var fAdapter: NewsAdapter
    private lateinit var recyclerViewF: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news, container, false)

        recyclerViewF = root.findViewById(R.id.recyclerViewFile)
        getRecyclerView()
        fAdapter.submitList(getDataSource())

        (activity as AppCompatActivity).supportActionBar?.show()

        return root
    }
    private fun getDataSource(): ArrayList<NewsModel>{
            val list = ArrayList<NewsModel>()
            list.add(NewsModel("Скоро в ваших ТСЖ будет возможно оплатить через  терминалы Pay24!","17.01.2020 7:41:19 AM"))
            list.add(NewsModel("Изменение тарифов  на коммунальные услуги","17.01.2020 7:41:19 AM"))
            list.add(NewsModel("О начислении ТКО","17.01.2020 7:41:19 AM"))
            list.add(NewsModel("Поправки в закон о Рекламе","17.01.2020 7:41:19 AM"))
            list.add(NewsModel("C 1 июля меняется схема субсидирования граждан при компенсации роста цен","17.01.2020 7:41:19 AM"))
            list.add(NewsModel("Скоро в ваших ТСЖ будет возможно оплатить через  терминалы Pay24!","17.01.2020 7:41:19 AM"))
            list.add(NewsModel("Изменение тарифов  на коммунальные услуги","17.01.2020 7:41:19 AM"))
            list.add(NewsModel("О начислении ТКО","17.01.2020 7:41:19 AM"))
            list.add(NewsModel("Поправки в закон о Рекламе","17.01.2020 7:41:19 AM"))
            list.add(NewsModel("C 1 июля меняется схема субсидирования граждан при компенсации роста цен","17.01.2020 7:41:19 AM"))
            return list
    }
    private fun getRecyclerView() {
        recyclerViewF.apply {
            fAdapter = NewsAdapter()
            adapter = fAdapter
        }
    }
}