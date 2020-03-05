package com.example.tsj.ui.requests


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import com.example.tsj.service.model.RequestAddressesModel
import com.example.tsj.service.model.RequestTypeModel
import com.example.tsj.service.request.AddRequest
import kotlinx.android.synthetic.main.fragment_bid_add.*
import kotlinx.android.synthetic.main.fragment_bid_add.view.*

class RequestAddFragment : Fragment() {


    private lateinit var viewModel: RequestViewModel
    private var requestTypeId = 0
    private var placementId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.show()
        val root = inflater.inflate(R.layout.fragment_bid_add, container, false)
        viewModel = ViewModelProviders.of(this).get(RequestViewModel::class.java)
        initViews(root)
        initData()
        return root
    }


    private fun initViews(root: View) {

        root.request_add.setOnClickListener {
            val body = AddRequest(
                placementId,
                requestTypeId,
                bid_add_porch.text.toString().toInt(),
                bid_add_flat.text.toString().toInt(),
                request_description.text.toString()
            )
            viewModel.addRequest(body).observe(this, Observer {
                if (it) {
                    Toast.makeText(context, "ok", Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(context, "ошибка", Toast.LENGTH_LONG).show()
                }
            })
        }

        //types
        root.bid_add_type.keyListener = null
        root.bid_add_type.setOnClickListener {
            root.bid_add_type.showDropDown()
        }
        root.bid_add_type.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                root.bid_add_type.showDropDown()
            }
        }
        root.bid_add_type.setOnItemClickListener { parent, _, position, _ ->
            requestTypeId =
                (parent.getItemAtPosition(position) as RequestTypeModel).requestTypeId
        }

        //addresses
        root.bid_add_adres.keyListener = null

        root.bid_add_adres.setOnClickListener {
            root.bid_add_adres.showDropDown()
        }



        root.bid_add_adres.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                bid_add_adres.showDropDown()
            }
        }

        root.bid_add_adres.setOnItemClickListener { parent, view, position, id ->
            placementId =
                (parent.getItemAtPosition(position) as RequestAddressesModel).placementId
        }
    }

    private fun initData() {
        viewModel.requestTypes().observe(this, Observer {
            //types
            val typeAdapter = ArrayAdapter<RequestTypeModel>(
                context!!,
                R.layout.support_simple_spinner_dropdown_item,
                it
            )
            bid_add_type.setAdapter(typeAdapter)


        })

        viewModel.requestAddresses().observe(this, Observer {
            //адреса
            val typeAdapter = ArrayAdapter<RequestAddressesModel>(
                context!!,
                R.layout.support_simple_spinner_dropdown_item,
                it
            )
            bid_add_adres.setAdapter(typeAdapter)
        })
    }


}
