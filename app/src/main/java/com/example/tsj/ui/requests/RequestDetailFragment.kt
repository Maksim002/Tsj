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

class RequestDetailFragment : Fragment() {

    private lateinit var viewModel: RequestViewModel
    private var requestId = 0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_bid_detail, container, false)
        viewModel = ViewModelProviders.of(this).get(RequestViewModel::class.java)
        (activity as AppCompatActivity).supportActionBar?.show()

        initArguments()
        initViews(root)
        initData()




        return root
    }

    private fun initData() {

    }

    private fun initViews(root: View) {

    }

    private fun initArguments() {
        requestId = try {
            arguments!!.getInt("id")
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


}