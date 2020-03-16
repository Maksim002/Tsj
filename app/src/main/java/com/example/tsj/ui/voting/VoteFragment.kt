package com.example.tsj.ui.voting


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.tsj.R
import com.example.tsj.adapters.message.ViewPagerAdapter
import com.example.tsj.ui.voting.tab.VoteEnd
import com.example.tsj.ui.voting.tab.VoteInProcess
import kotlinx.android.synthetic.main.fragment_vote.*


class VoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.show()
        return inflater.inflate(R.layout.fragment_vote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabLayout()
    }

    private fun initTabLayout() {
        val voteVpAdapter = ViewPagerAdapter(activity!!.supportFragmentManager)
        voteVpAdapter.addFragment(VoteInProcess(), getString(R.string.inProcess))
        voteVpAdapter.addFragment(VoteEnd(), getString(R.string.VoteEnd))
        vote_vp.adapter = voteVpAdapter
        vote_tablayout.setupWithViewPager(vote_vp)
     }
}
