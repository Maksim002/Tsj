package com.example.tsj.ui.voting.tab


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import com.example.tsj.adapters.vote.VoteAdapter
import com.example.tsj.adapters.vote.VoteItemClickListener
import com.example.tsj.model.VoteModel
import com.example.tsj.ui.voting.VoteViewModel
import kotlinx.android.synthetic.main.fragment_vote_in_process.*

class VoteInProcessFragment(private val placementId : Int) : Fragment(), VoteItemClickListener {
    private lateinit var viewModel: VoteViewModel
    private val typeId = 2
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
        viewModel.voteList(typeId, placementId).observe(this, Observer {
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

    override fun onVoteItemClicked(model: VoteModel) {
        findNavController().navigate(R.id.navigation_vote_detail)
    }
}
