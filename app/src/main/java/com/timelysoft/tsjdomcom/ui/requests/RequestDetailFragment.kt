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
    private var requestDate = ""

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
                    bid_detail_date.text = "от " + MyUtils.toMyDateTime(requestDate)

                    if (data.editableAndCloseable) {
                        bid_status_textview.text = "Создана"
                        bid_status_textview.setTextColor(resources.getColor(R.color.requestStatusGreen))
                        bid_status_view.setBackgroundColor(resources.getColor(R.color.requestStatusGreen))
                    } else {
                        bid_status_textview.text = "Отменена"
                        bid_status_textview.setTextColor(resources.getColor(R.color.requestStatusRed))
                        bid_status_view.setBackgroundColor(resources.getColor(R.color.requestStatusRed))
                    }
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

        requestDate = try {
            arguments!!.getString("date")!!
        } catch (e: Exception) {
            " "
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
