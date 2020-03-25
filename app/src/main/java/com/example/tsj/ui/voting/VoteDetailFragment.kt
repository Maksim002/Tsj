package com.example.tsj.ui.voting


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
import com.example.tsj.MainActivity
import com.example.tsj.R
import com.example.tsj.adapters.vote.VoteDetailAdapter
import com.example.tsj.service.request.VotingRequest
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
        // Inflate the layout for this fragment
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
            viewModel.voteVariants(questionId).observe(this, Observer {
                for (element in it) {
                    val radioButton = RadioButton(context)
                    radioButton.id = element.id
                    radioButton.text = element.name
                    vote_detail_radiogroup.addView(radioButton)
                }

                MainActivity.alert.hide()
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
                viewModel.votingPost(body).observe(this, Observer {
                    MainActivity.alert.hide()
                    if (it) {
                        vote_detail_radiogroup.visibility = View.GONE
                        vote_detail_rv.visibility = View.VISIBLE
                        vote_detail_accept_btn.visibility = View.VISIBLE
                        getVotedVariants()
                        Toast.makeText(context, "Вы уже проголосовали!", Toast.LENGTH_LONG).show()
                        vote_detail_accept_btn.visibility = View.GONE
                    } else {
                Toast.makeText(context, "Что-то пошло не так", Toast.LENGTH_LONG).show()
            }
            })
        } else {
                Toast.makeText(context, "Вы не выбрали вариант", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getVotedVariants() {
        MainActivity.alert.show()
        viewModel.voteDetail(questionId).observe(this, Observer {
            val adapter = VoteDetailAdapter(it.variants)
            vote_detail_rv.adapter = adapter
            MainActivity.alert.hide()
            var count = 0
            it.variants.forEach {
                count += it.count
            }
            vote_count.text = "$count голосов"

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
