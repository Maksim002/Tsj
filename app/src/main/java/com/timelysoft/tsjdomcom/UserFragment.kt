package com.timelysoft.tsjdomcom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.adapters.user.UserAdapter
import com.timelysoft.tsjdomcom.adapters.user.UserModel
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {
    private var myAdapter =  UserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecyclerView()
    }

    private fun initRecyclerView() {

        var list: ArrayList<UserModel> = arrayListOf()

        list.add(UserModel("", "", "Каримова Жазгуль Калбековна 1", "", "", false, ""))
        list.add(UserModel("", "", "Каримова Жазгуль Калбековна 2", "", "", false, ""))
        list.add(UserModel("", "", "Каримова Жазгуль Калбековна 3", "", "", false, ""))
        list.add(UserModel("", "", "Каримова Жазгуль Калбековна 4", "", "", false, ""))
        list.add(UserModel("", "", "Каримова Жазгуль Калбековна 5", "", "", false, ""))

        myAdapter.update(list)

        user_recycler.adapter = myAdapter
    }
}