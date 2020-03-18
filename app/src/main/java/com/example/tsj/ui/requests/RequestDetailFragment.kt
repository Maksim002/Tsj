package com.example.tsj.ui.requests


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import kotlinx.android.synthetic.main.fragment_bid_detail.*
import com.example.tsj.service.model.RequestModel
import kotlinx.android.synthetic.main.fragment_bid_detail.view.*

class RequestDetailFragment : Fragment() {

    private lateinit var viewModel: RequestViewModel
    private var requestId = 0;
    private var detailsId = 0;

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
        initViews(root)
        initData(root)
        return root
    }


    private fun initData(root: View) {
        viewModel.getRequest(requestId).observe(this, Observer {
            requestModel = it
            root.bid_adres_content.text = it.address
            root.bid_flat_content.text = it.floor.toString()
            root.bid_porch_content.text = it.entrance.toString()
            root.bid_description_content.text = it.description
            root.bid_title.text = it.requestTypeName
        })
    }

    private fun initViews(root: View) {
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
                //edit
                editRequest()
            }
            R.id.request_delete -> {
                //delete
                deleteRequest()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun editRequest() {

        findNavController().navigate(R.id.navigation_bid_add)
    }

    private fun deleteRequest() {
        viewModel.deleteRequest(requestId).observe(this, Observer {
            setHasOptionsMenu(false)
            if (it) {
                Toast.makeText(context, "ok", Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(context, "ошибка", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onStart() {
        super.onStart()

        viewModel.detailsModel(requestId).observe(this, Observer { list ->
            bid_adres_content.setText(list.address)
            bid_flat_content.setText(list.entrance.toString())
            bid_description_content.setText(list.description)
            bid_porch_content.setText(list.floor.toString())
        })
    }
}
