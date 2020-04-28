package com.timelysoft.tsjdomcom.ui.voting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.vote.VoteAdapter
import com.timelysoft.tsjdomcom.adapters.vote.VoteItemClickListener
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.VoteModel
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_vote_in_process.*

class VoteInProcessFragment(private val placementId: Int, private val typeId: Int) : Fragment(),
    VoteItemClickListener {

    private lateinit var viewModel: VoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(VoteViewModel::class.java)
        return inflater.inflate(R.layout.fragment_vote_in_process, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        MainActivity.alert.show()

        viewModel.votesN(typeId, placementId).observe(viewLifecycleOwner, Observer { result ->
           val msg = result.msg
           val data = result.data
            MainActivity.alert.hide()
            when(result.status){
                Status.SUCCESS ->{
                    val voteAdapter = VoteAdapter(this, data!!)
                    vote_inprocess_rv.adapter = voteAdapter
                    if (voteAdapter.itemCount == 0) {
                        vote_inprocess_rv.visibility = View.GONE
                        vote_inprocess_empty.visibility = View.VISIBLE
                    } else {
                        vote_inprocess_rv.visibility = View.VISIBLE
                        vote_inprocess_empty.visibility = View.GONE
                    }
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onClick(model: VoteModel, isCanVote: Boolean) {
        val bundle = Bundle()
        val endDate = MyUtils.toMyDate(model.endDate)
        bundle.putString("date", endDate)
        bundle.putInt("id", model.id)
        bundle.putInt("placementId", placementId)
        bundle.putString("question", model.question)
        bundle.putBoolean("isCanVote", isCanVote)
        findNavController().navigate(R.id.navigation_vote_detail, bundle)
    }


}
