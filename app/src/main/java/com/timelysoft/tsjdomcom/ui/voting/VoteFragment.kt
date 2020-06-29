package com.timelysoft.tsjdomcom.ui.voting


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
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.message.ViewPagerAdapter
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.model.AddressModel
import com.timelysoft.tsjdomcom.service.model.MessagesPersonsModel
import kotlinx.android.synthetic.main.fragment_vote.*

class VoteFragment : Fragment() {

    private lateinit var viewModel: VoteViewModel
    private var placementId: Int = 0
    private lateinit var votingTypes: List<MessagesPersonsModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(VoteViewModel::class.java)

        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.show()
        return inflater.inflate(R.layout.fragment_vote, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (vote_address.text.isNotEmpty())
            vote_address_out.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getVoteAddress()


        if (placementId != 0 && votingTypes != null) {
            initTabLayout(placementId, votingTypes)
        }

        vote_unfocus.requestFocus()
    }

    private fun getVoteAddress() {
        MainActivity.alert.show()
        //запрос на typeId для tabLayout

        viewModel.voteTypes().observe(viewLifecycleOwner, Observer { result ->
            val msg = result.msg
            val data = result.data
            when(result.status){
                Status.SUCCESS ->{
                    votingTypes = data!!
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })

        //запрос на список адреса для autoComplate

        viewModel.voteAddress().observe(viewLifecycleOwner, Observer { result->
            val msg = result.msg
            val data = result.data
            when(result.status){
                Status.SUCCESS ->{
                    MainActivity.alert.hide()
                    val addressAdapter = ArrayAdapter<AddressModel>(
                        context!!,
                        android.R.layout.simple_dropdown_item_1line,
                        data!!
                    )
                    vote_address.setAdapter(addressAdapter)
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })

        vote_address.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                vote_address_out.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
                parent.getItemAtPosition(position).toString()
                placementId = (parent.getItemAtPosition(position) as AddressModel).placementId

                initTabLayout(placementId, votingTypes)
                vote_unfocus.requestFocus()
            }

        vote_address.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                vote_address.showDropDown()
            } else {
                vote_unfocus.requestFocus()
            }

        }

        vote_address.setOnClickListener {
            vote_address.showDropDown()
        }

    }

    private fun initTabLayout(placementId: Int, votingTypes: List<MessagesPersonsModel>) {
        vote_unfocus.requestFocus()
        val voteVpAdapter = ViewPagerAdapter(childFragmentManager)
        votingTypes.forEach {
            voteVpAdapter.addFragment(
                VoteInProcessFragment(
                    placementId,
                    it.id
                ), it.name
            )
        }
        vote_vp.adapter = voteVpAdapter
        vote_tablayout.setupWithViewPager(vote_vp)
        vote_not_selected.visibility = View.GONE
    }
}
