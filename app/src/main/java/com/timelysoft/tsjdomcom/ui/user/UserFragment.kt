package com.timelysoft.tsjdomcom.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.user.UserAdapter
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.user.UserChairmanModel
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {
    private var myAdapter = UserAdapter(ArrayList())

    private lateinit var viewModel: UserViewModel

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
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        MainActivity.alert.show()
        user_recycler.adapter = myAdapter
        viewModel.user().observe(viewLifecycleOwner, Observer { result ->
            val msg = result.msg
            val data = result.data
            when (result.status) {
                Status.SUCCESS -> {
                    myAdapter.update(data!!.users as ArrayList<UserChairmanModel>)
                    myAdapter.notifyDataSetChanged()
                    MainActivity.alert.hide()
                }
                Status.ERROR, Status.NETWORK -> {
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}