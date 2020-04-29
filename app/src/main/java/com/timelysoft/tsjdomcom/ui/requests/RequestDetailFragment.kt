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
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_bid_detail.*

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
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        MainActivity.alert.show()
        viewModel.getRequest(requestId).observe(viewLifecycleOwner, Observer { result ->
            val msg = result.msg
            val data = result.data
            MainActivity.alert.hide()
            when(result.status){
                Status.SUCCESS ->{
                    requestModel = data!!
                    bid_adres_content.text = data.address
                    bid_flat_content.text = data.floor.toString()
                    bid_porch_content.text = data.entrance.toString()
                    bid_description_content.text = data.description
                    bid_title.text = data.requestTypeName
                    bid_detail_date_creat.text = "Дата создания " + MyUtils.toMyDateTime(data.createdDate)
                    bid_detail_date_сhange.text = "Дата изменения " + MyUtils.toMyDateTime(data.statusDate)
                    bid_status_text_view.text = data.statusName

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
            MainActivity.alert.hide()
            when(result.status){
                Status.SUCCESS ->{
                    findNavController().popBackStack()
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}
