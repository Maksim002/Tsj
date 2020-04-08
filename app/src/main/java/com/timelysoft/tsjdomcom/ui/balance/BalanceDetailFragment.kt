package com.timelysoft.tsjdomcom.ui.balance


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.balance.BalanceAdapter
import kotlinx.android.synthetic.main.fragment_balance_detail.*

class BalanceDetailFragment : Fragment() {
    private lateinit var viewModel: BalanceViewModel
    private lateinit var balanceAdapter: BalanceAdapter
    private lateinit var balance_rv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.show()
        val root = inflater.inflate(R.layout.fragment_balance_detail, container, false)


        viewModel = ViewModelProviders.of(this).get(BalanceViewModel::class.java)
        balance_rv = root.findViewById(R.id.balance_detail_rv)
        getRecyclerView()

        return root

    }

    private fun getRecyclerView() {
        balanceAdapter = BalanceAdapter()
        balance_rv.adapter =  balanceAdapter

    }

    override fun onStart() {
        super.onStart()
        val id = try {
            arguments!!.getInt("placementId")
        } catch (e: Exception) {
            0
        }

        balance_detail_address.text = try {
            arguments!!.getString("address")
        } catch (e: Exception) {
            ""
        }
        MainActivity.alert.show()
        viewModel.services(id).observe(this, Observer { list ->
            balanceAdapter.setList(list)
            MainActivity.alert.hide()
        })
    }
}
