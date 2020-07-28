package com.timelysoft.tsjdomcom.ui.voice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.voice.VotingAdapter
import com.timelysoft.tsjdomcom.adapters.voice.VotingListener
import com.timelysoft.tsjdomcom.adapters.voice.VotingModel
import kotlinx.android.synthetic.main.fragment_voting.*

class VotingFragment : Fragment(), VotingListener {

    private var visibility: Boolean = false

    private var myAdapter = VotingAdapter(this)
    val list: ArrayList<VotingModel> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_voting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        initRecycler()
    }

    private fun initRecycler() {
        list.add(VotingModel(""))
        list.add(VotingModel(""))
        list.add(VotingModel(""))
        list.add(VotingModel(""))

        myAdapter.update(list)
        voting_recycler.adapter = myAdapter
    }

    override fun onVotingClickListener(item: VotingModel) {
        if (visibility){
            voting_save.visibility = View.GONE
        }else{
            voting_save.visibility = View.VISIBLE
        }
    }
}