package com.example.tsj.ui.balance


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.tsj.MainActivity
import com.example.tsj.R
import com.example.tsj.adapters.balance.BalanceAdapter
import kotlinx.android.synthetic.main.fragment_balance_detail.*

class BalanceDetailFragment : Fragment() {
    private lateinit var viewModel: BalanceDetailViewModel
    private lateinit var balanceAdapter: BalanceAdapter
    private lateinit var balance_rv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.show()
        val root = inflater.inflate(R.layout.fragment_balance_detail, container, false)


        viewModel = ViewModelProviders.of(this).get(BalanceDetailViewModel::class.java)
        MainActivity.alert.show()
        balance_rv = root.findViewById(R.id.balance_summ_recyclerview)
        getRecyclerView()

        return root

    }

    private fun getRecyclerView() {
        balanceAdapter = BalanceAdapter()
        balance_rv.apply {
            adapter = balanceAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        val id = try {
            arguments!!.getInt("placementId")
        } catch (e: Exception) {
            0
        }

        textBalance.text = try {
            arguments!!.getString("address")
        } catch (e: Exception) {
            ""
        }

        viewModel.services(id).observe(this, Observer { list ->
            balanceAdapter.setList(list)
            MainActivity.alert.hide()
        })
    }
}
