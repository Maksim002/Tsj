package com.example.tsj.ui.history.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

import com.example.tsj.R
import com.example.tsj.adapters.save.SaveRecyclerAdapter
import com.example.tsj.adapters.save.SaveListener
import com.example.tsj.model.SaveModel
import java.util.*
import kotlin.collections.ArrayList

class SaveFragment : Fragment(), SaveListener {

    private lateinit var recycler: RecyclerView
    private lateinit var adapters: SaveRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_save, container, false)

        recycler = root.findViewById(R.id.save_recyclerView)
        adapters = SaveRecyclerAdapter(getList(), this)
        recycler.adapter = adapters


        (activity as AppCompatActivity).supportActionBar?.show()
        return root
    }

    private fun getList(): ArrayList<SaveModel> {
        var list = ArrayList<SaveModel>()
        list.add(SaveModel(1, "7 небо, Токомбаева, д.53/2 кв 11", "Лицевой счет №2425"))
        list.add(SaveModel(2, "7 небо, Игенбердиева, д.53/2 кв 12", "Лицевой счет №2426"))
        list.add(SaveModel(3, "7 небо, Токомбаева, д.53/2 кв 13", "Лицевой счет №2427"))
        list.add(SaveModel(4, "7 небо, Смалова, д.53/2 кв 14", "Лицевой счет №2428"))
        list.add(SaveModel(5, "7 небо, Токомбаева, д.53/2 кв 15", "Лицевой счет №2429"))
        list.add(SaveModel(6, "7 небо, Тошконбаева, д.53/2 кв 16", "Лицевой счет №24210"))
        return list
    }
    override fun onClick(save: SaveModel) {
        val bundle = Bundle()
        bundle.putInt("id", save.id)
        bundle.putString("title",save.title)
        Navigation.findNavController(Objects.requireNonNull<View>(view))
            .navigate(R.id.navigation_Fragment, bundle)
    }
}
