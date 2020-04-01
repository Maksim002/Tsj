package com.timelysoft.tsjdomcom.ui.voting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.vote.VoteAdapter
import com.timelysoft.tsjdomcom.adapters.vote.VoteItemClickListener
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

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vote_in_process, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRec()
    }

    private fun initRec() {
        MainActivity.alert.show()
        viewModel.voteList(typeId, placementId).observe(this, Observer {
            MainActivity.alert.hide()
            val voteActiveAdapter = VoteAdapter(this, it)
            vote_inprocess_rv.apply { adapter = voteActiveAdapter }
            if (voteActiveAdapter.itemCount == 0) {
                vote_inprocess_rv.visibility = View.GONE
                vote_inprocess_empty.visibility = View.VISIBLE
            } else {
                vote_inprocess_rv.visibility = View.VISIBLE
                vote_inprocess_empty.visibility = View.GONE
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