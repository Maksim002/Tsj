package com.example.tsj.ui.voting.tab


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import com.example.tsj.adapters.vote.VoteAdapter
import com.example.tsj.adapters.vote.VoteItemClickListener
import com.example.tsj.model.VoteModel
import kotlinx.android.synthetic.main.fragment_vote_end.*

class VoteEnd : Fragment(), VoteItemClickListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vote_end, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = listOf(
            VoteModel(0, "uikjbhgy7u8jhgftyguijhg", " "),
            VoteModel(0, "uikjbhgy7u8jhgftyguijhg", " "),
            VoteModel(0, "uikjbhgy7u8jhgftyguijhg", " "),
            VoteModel(0, "uikjbhgy7u8jhgftyguijhg", " "),
            VoteModel(0, "uikjbhgy7u8jhgftyguijhg", " "),
            VoteModel(0, "uikjbhgy7u8jhgftyguijhg", " "),
            VoteModel(0, "uikjbhgy7u8jhgftyguijhg", " "),
            VoteModel(0, "uikjbhgy7u8jhgftyguijhg", " "),
            VoteModel(0, "uikjbhgy7u8jhgftyguijhg", " ")
            )

        val adapter = VoteAdapter(this, item)
        vote_end_rv.adapter = adapter
    }

    override fun onVoteItemClicked(model: VoteModel) {
        findNavController().navigate(R.id.navigation_vote_detail)
    }
}
