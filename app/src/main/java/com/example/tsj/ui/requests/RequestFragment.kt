package com.example.tsj.ui.requests


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import com.example.tsj.adapters.bid.RequestAdapter
import com.example.tsj.model.BidModel
import com.example.tsj.adapters.bid.RequestClickItemListener
import com.example.tsj.service.model.RequestsModel
import kotlinx.android.synthetic.main.fragment_bid.*
import kotlinx.android.synthetic.main.fragment_bid.view.*
import java.util.*
import kotlin.collections.ArrayList

class RequestFragment : Fragment(), RequestClickItemListener {
    private lateinit var viewModel: RequestViewModel
    private lateinit var requestAdapter: RequestAdapter
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
        viewModel.requests().observe(this, Observer {
            if (it.isNotEmpty()) {
                requestAdapter.update(it as ArrayList<RequestsModel>)
                bid_is_empty_textview.visibility = View.GONE
                bid_recyclerview.visibility = View.VISIBLE
            } else {
                bid_is_empty_textview.visibility = View.VISIBLE
                bid_recyclerview.visibility = View.GONE
            }


        })
    }

    private fun initViews(root: View) {
        root.bid_create.setOnClickListener {
            RequestDetailFragment.requestModel.id = null
            findNavController().navigate(R.id.navigation_bid_add)
        }

        requestAdapter = RequestAdapter(this)
        root.bid_recyclerview.adapter = requestAdapter
    }


    override fun onClickRequest(item: RequestsModel) {
        val bundle = Bundle()
        bundle.putInt("id", item.id)
        bundle.putBoolean("isEditableAndCloseable", item.isEditableAndCloseable)
        Navigation.findNavController(Objects.requireNonNull<View>(view))
            .navigate(R.id.navigation_bid_detail, bundle)
    }

}
