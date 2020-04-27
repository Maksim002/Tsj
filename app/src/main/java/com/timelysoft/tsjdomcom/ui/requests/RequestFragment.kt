package com.timelysoft.tsjdomcom.ui.requests


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.bid.RequestAdapter
import com.timelysoft.tsjdomcom.adapters.bid.RequestClickItemListener
import com.timelysoft.tsjdomcom.service.model.RequestsModel
import kotlinx.android.synthetic.main.fragment_bid.*
import kotlinx.android.synthetic.main.fragment_bid.view.*
import java.util.*
import kotlin.collections.ArrayList

class RequestFragment : Fragment(), RequestClickItemListener {
    private lateinit var viewModel: RequestViewModel
    private lateinit var requestAdapter: RequestAdapter
    private var requestsId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(RequestViewModel::class.java)
        (activity as AppCompatActivity).supportActionBar?.show()
        val root = inflater.inflate(R.layout.fragment_bid, container, false)

        initViews(root)
        initData()
        return root
    }

    private fun initData() {
        MainActivity.alert.show()
        viewModel.requests().observe(this, Observer {address ->

            AdapterView.OnItemClickListener{ _, _, position, _ ->
                requestsId = address[position].id
            }

            if (address.isNotEmpty()) {
                requestAdapter.update(address)
                bid_is_empty_textview.visibility = View.GONE
                bid_recyclerview.visibility = View.VISIBLE
            } else {
                bid_is_empty_textview.visibility = View.VISIBLE
                bid_recyclerview.visibility = View.GONE
            }

            MainActivity.alert.hide()
        })
    }

    private fun initViews(root: View) {
        root.bid_create.setOnClickListener {
            RequestDetailFragment.requestModel.id = 0
            findNavController().navigate(R.id.navigation_bid_add)
        }

        requestAdapter = RequestAdapter(this)
        root.bid_recyclerview.adapter = requestAdapter
    }


    override fun onClickRequest(item: RequestsModel) {
        val bundle = Bundle()
        bundle.putInt("id", item.id)
        bundle.putInt("requestsId", requestsId)
        bundle.putString("date",item.dateArrival)
        bundle.putBoolean("isEditableAndCloseable", item.isEditableAndCloseable)
        Navigation.findNavController(Objects.requireNonNull<View>(view))
            .navigate(R.id.navigation_bid_detail, bundle)
    }

}
