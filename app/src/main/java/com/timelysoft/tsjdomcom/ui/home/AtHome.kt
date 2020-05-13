package com.timelysoft.tsjdomcom.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.home.AtHomeAdapter
import com.timelysoft.tsjdomcom.adapters.home.AtHomeModel
import kotlinx.android.synthetic.main.fragment_navigation_at_home.*
import kotlin.collections.ArrayList

class AtHome : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_navigation_at_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

    }
    fun initRecyclerView(){
        val list: ArrayList<AtHomeModel> = ArrayList()
        list.add(AtHomeModel("Активен","Бейшенкулова Ж. Ж.","Прописка 8-46-5","Заблокировать","Зарегистрировать","",""))
        list.add(AtHomeModel("Активен","Бейшенкулова Ж. Ж.","Прописка 8-46-5","Заблокировать","","","tsjreceiver@yandex.ru"))
        list.add(AtHomeModel("Активен","Бейшенкулова Ж. Ж.","Прописка 8-46-5","Заблокировать","","","ermek_akmatov@inbox.ru"))
        list.add(AtHomeModel("Активен","Бейшенкулова Ж. Ж.","Прописка 8-46-5","Заблокировать","Зарегистрировать","",""))
        list.add(AtHomeModel("Активен","Бейшенкулова Ж. Ж.","Прописка 8-46-5","Заблокировать","Зарегистрировать","",""))
        list.add(AtHomeModel("Активен","Бейшенкулова Ж. Ж.","Прописка 8-46-5","Заблокировать","Зарегистрировать","",""))

        val adapters = AtHomeAdapter()
        adapters.update(list)
        at_home_recycler_view.adapter = adapters

    }
}
