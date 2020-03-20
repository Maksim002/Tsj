package com.example.tsj.ui.voting


import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.tsj.MainActivity
import com.example.tsj.R
import com.example.tsj.adapters.message.ViewPagerAdapter
import com.example.tsj.service.model.AddressModel
import com.example.tsj.service.model.MessagesPersonsModel
import com.example.tsj.ui.voting.tab.VoteInProcessFragment
import kotlinx.android.synthetic.main.fragment_vote.*

//dastan
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
        (activity as AppCompatActivity).supportActionBar?.hide()
        return inflater.inflate(R.layout.fragment_vote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getVoteAddress()
        if (placementId!=0 && votingTypes!=null){
            initTabLayout(placementId,votingTypes)
        }
        vote_unfocus.requestFocus()
    }

    private fun getVoteAddress() {
        MainActivity.alert.show()
        //запрос на typeId для tabLayout
        viewModel.voteTypes().observe(this, Observer {
            votingTypes = it
        })

        //запрос на список адреса для autoComplate
        viewModel.voteAddress().observe(this, Observer {
            MainActivity.alert.hide()
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

                initTabLayout(placementId, votingTypes)
                vote_unfocus.requestFocus()
            }

        vote_address_auto.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                vote_address_auto.showDropDown()
            }else{
                vote_unfocus.requestFocus()
            }

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

    private fun initTabLayout(placementId: Int, votingTypes : List <MessagesPersonsModel> ) {
        vote_unfocus.requestFocus()
        val voteVpAdapter = ViewPagerAdapter(childFragmentManager)
        votingTypes.forEach {
            voteVpAdapter.addFragment(VoteInProcessFragment(placementId, it.id), it.name)
        }
        vote_vp.adapter = voteVpAdapter
        vote_tablayout.setupWithViewPager(vote_vp)
        vote_not_selected.visibility = View.GONE
    }
}
