package com.example.tsj.ui.voting


import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.tsj.R
import com.example.tsj.adapters.message.ViewPagerAdapter
import com.example.tsj.service.model.AddressModel
import com.example.tsj.ui.voting.tab.VoteEndFragment
import com.example.tsj.ui.voting.tab.VoteInProcessFragment
import kotlinx.android.synthetic.main.fragment_vote.*


class VoteFragment : Fragment() {

    private lateinit var viewModel: VoteViewModel

    var placementId: Int = 0

    //dastan
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(VoteViewModel::class.java)

        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.hide()
        return inflater.inflate(R.layout.fragment_vote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getVoteAddress()
        vote_search_img.setOnClickListener {
            Toast.makeText(context, "id $placementId", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getVoteAddress() {
        viewModel.voteAddress().observe(this, Observer {

            val addressAdapter = ArrayAdapter<AddressModel>(
                context!!,
                android.R.layout.simple_dropdown_item_1line,
                it
            )
            vote_address_auto.setAdapter(addressAdapter)
        })

        vote_address_auto.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                vote_address.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                parent.getItemAtPosition(position).toString()
                placementId = (parent.getItemAtPosition(position) as AddressModel).placementId
                initTabLayout(placementId)
            }

        vote_address_auto.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            vote_address_auto.showDropDown()
        }

        vote_address_auto.setOnClickListener {
            vote_address_auto.showDropDown()
        }

    }

    private fun initViews() {
        vote_back_btn.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun initTabLayout(placementId : Int) {
        val voteVpAdapter = ViewPagerAdapter(activity!!.supportFragmentManager)
        voteVpAdapter.addFragment(VoteInProcessFragment(placementId), getString(R.string.inProcess))
        voteVpAdapter.addFragment(VoteEndFragment(placementId), getString(R.string.VoteEnd))
        vote_vp.adapter = voteVpAdapter
        vote_tablayout.setupWithViewPager(vote_vp)
        vote_not_selected.visibility = View.GONE
    }
}
