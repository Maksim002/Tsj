package com.example.tsj.ui.voting.detail


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
import com.example.tsj.R
import com.example.tsj.service.request.VotingRequest
import com.example.tsj.ui.voting.VoteViewModel
import kotlinx.android.synthetic.main.fragment_vote_detail.*
import java.lang.Exception

class VoteDetailFragment : Fragment() {
    private lateinit var viewModel: VoteViewModel
    private var questionId: Int = 0
    private var question: String = " "
    private var endDate: String = " "
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
        initPost()
    }

    private fun initVariants() {
        viewModel.voteVariants(questionId).observe(this, Observer {
            for (element in it) {
                val radioButton = RadioButton(context)
                radioButton.id = element.id
                radioButton.text = element.name
                vote_detail_radiogroup.addView(radioButton)
            }
        })

        vote_detail_enddate.text = "Дата окончания: $endDate"
        vote_detail_question.text = question
    }

    private fun initPost() {
        profile_accept_btn.setOnClickListener {
            val variantId = vote_detail_radiogroup.checkedRadioButtonId
            val body = VotingRequest (questionId, variantId, placementId)
            viewModel.votingPost(body).observe(this, Observer {
                if (it){
                    Toast.makeText(context, "ОК", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                }
            })
        }
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
    }

}
