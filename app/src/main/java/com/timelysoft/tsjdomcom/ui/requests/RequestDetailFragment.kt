package com.timelysoft.tsjdomcom.ui.requests


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.RequestModel
import kotlinx.android.synthetic.main.fragment_bid_detail.view.*

class RequestDetailFragment : Fragment() {

    private lateinit var viewModel: RequestViewModel
    private var requestId = 0
    private var detailsId = 0

    companion object {
        var requestModel = RequestModel()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_bid_detail, container, false)
        viewModel = ViewModelProviders.of(this).get(RequestViewModel::class.java)
        (activity as AppCompatActivity).supportActionBar?.show()
        initArguments()
        initData(root)
        return root
    }


    private fun initData(root: View) {
        MainActivity.alert.show()
        viewModel.getRequest(requestId).observe(viewLifecycleOwner, Observer { result ->
            val msg = result.msg
            val data = result.data
            when(result.status){
                Status.SUCCESS ->{
                    requestModel = data!!
                    root.bid_adres_content.text = data.address
                    root.bid_flat_content.text = data.floor.toString()
                    root.bid_porch_content.text = data.entrance.toString()
                    root.bid_description_content.text = data.description
                    root.bid_title.text = data.requestTypeName
                    MainActivity.alert.hide()
                }
                Status.ERROR, Status.NETWORK ->{
                   Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun initArguments() {
        requestId = try {
            arguments!!.getInt("id")
        } catch (e: Exception) {
            0
        }

        detailsId = try {
            arguments!!.getInt("detailsId")
        } catch (e: Exception) {
            0
        }

        try {
            if (arguments!!.getBoolean("isEditableAndCloseable"))
                setHasOptionsMenu(true)
        } catch (e: Exception) {

        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.request_detail_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.request_edit -> {
                editRequest()
            }
            R.id.request_delete -> {
                deleteRequest()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun editRequest() {
        findNavController().navigate(R.id.navigation_bid_add)
    }

    private fun deleteRequest() {
        MainActivity.alert.show()
        viewModel.deleteRequest(requestId).observe(this, Observer { result ->
            val msg = result.msg
            when(result.status){
                Status.SUCCESS ->{
                    findNavController().popBackStack()
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}
