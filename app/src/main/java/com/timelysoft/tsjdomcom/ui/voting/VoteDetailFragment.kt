package com.timelysoft.tsjdomcom.ui.voting


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.timelysoft.tsjdomcom.ui.main.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.adapters.vote.VoteDetailAdapter
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.request.VotingRequest
import kotlinx.android.synthetic.main.fragment_vote_detail.*
import java.lang.Exception

class VoteDetailFragment : Fragment() {
    private lateinit var viewModel: VoteViewModel
    private var questionId: Int = 0
    private var question: String = " "
    private var endDate: String = " "
    private var isCanVote = false
    private var placementId = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(VoteViewModel::class.java)

        (activity as AppCompatActivity).supportActionBar!!.show()
        return inflater.inflate(R.layout.fragment_vote_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArguments()
        initVariants()
        initPostVariants()
    }

    private fun initVariants() {
        if (isCanVote) {
            MainActivity.alert.show()

            viewModel.voteVariants(questionId).observe(viewLifecycleOwner, Observer { result ->
                val msg = result.msg
                val data = result.data
                when(result.status){
                    Status.SUCCESS ->{
                        for (element in data!!) {
                            val radioButton = RadioButton(context)
                            radioButton.id = element.id
                            radioButton.text = element.name
                            vote_detail_radiogroup.addView(radioButton)
                        }

                        MainActivity.alert.hide()
                    }
                    Status.ERROR, Status.NETWORK ->{
                        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                    }
                }
            })
        } else {
            vote_detail_radiogroup.visibility = View.GONE
            vote_detail_rv.visibility = View.VISIBLE
            vote_detail_accept_btn.visibility = View.GONE
            getVotedVariants()
        }

        vote_detail_enddate.text = "Дата окончания: $endDate"
        vote_detail_question.text = question
    }

    private fun initPostVariants() {
        vote_detail_accept_btn.setOnClickListener {
            val variantId = vote_detail_radiogroup.checkedRadioButtonId
            if (variantId != -1) {
                val body = VotingRequest(questionId, variantId, placementId)
                MainActivity.alert.show()

                viewModel.votingPost(body).observe(viewLifecycleOwner, Observer { result ->
                    val msg = result.msg
                    MainActivity.alert.hide()
                    when(result.status){
                        Status.SUCCESS ->{
                                vote_detail_radiogroup.visibility = View.GONE
                                vote_detail_rv.visibility = View.VISIBLE
                                vote_detail_accept_btn.visibility = View.VISIBLE
                                getVotedVariants()
                                Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                                vote_detail_accept_btn.visibility = View.GONE
                        }
                        Status.ERROR, Status.NETWORK ->{
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                        }
                    }
                })
            } else {
                Toast.makeText(context, "Вы не выбрали вариант", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getVotedVariants() {
        MainActivity.alert.show()

        viewModel.voteDetail(questionId).observe(viewLifecycleOwner, Observer { result ->
            val msg = result.msg
            val data = result.data
            when(result.status){
                Status.SUCCESS ->{
                    val adapter = VoteDetailAdapter(data!!.variants)
                    vote_detail_rv.adapter = adapter
                    MainActivity.alert.hide()
                    var count = 0
                    data.variants.forEach {
                        count += it.count
                    }
                    vote_count.text = "$count голосов"
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun initArguments() {
        questionId = try {
            arguments!!.getInt("id")
        } catch (e: Exception) {
            0
        }

        question = try {
            arguments!!.getString("question")!!
        } catch (e: Exception) {
            "0"
        }

        endDate = try {
            arguments!!.getString("date")!!
        } catch (e: Exception) {
            "0"
        }

        placementId = try {
            arguments!!.getInt("placementId")
        } catch (e: Exception) {
            0
        }

        isCanVote = try {
            arguments!!.getBoolean("isCanVote")
        } catch (e: Exception) {
            false
        }
    }
}
