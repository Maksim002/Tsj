package com.example.tsj.ui.voting.tab


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import com.example.tsj.adapters.vote.VoteAdapter
import com.example.tsj.adapters.vote.VoteItemClickListener
import com.example.tsj.model.VoteModel
import com.example.tsj.ui.voting.VoteFragment
import com.example.tsj.ui.voting.VoteViewModel
import kotlinx.android.synthetic.main.fragment_vote.*
import kotlinx.android.synthetic.main.fragment_vote_end.*

class VoteEndFragment(private val placementId : Int) : Fragment(), VoteItemClickListener {
    private lateinit var viewModel: VoteViewModel
    private val typeId: Int = 3

//dastan

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(VoteViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vote_end, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRec()

    }

    private fun initRec() {
        viewModel.voteList(typeId, placementId).observe(this, Observer {
            val voteEndAdpter = VoteAdapter(this, it)
            vote_end_rv.apply {
                adapter = voteEndAdpter
            }
            if (placementId == 0) {
            vote_end_rv.visibility = View.GONE
            vote_end_empty.visibility = View.VISIBLE
        } else {
            vote_end_rv.visibility = View.VISIBLE
            vote_end_empty.visibility = View.GONE
        }
        })
    }

    override fun onVoteItemClicked(model: VoteModel) {
        findNavController().navigate(R.id.navigation_vote_detail)
    }

}
