package com.timelysoft.tsjdomcom.ui.feedback


import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.AppPreferences
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.service.request.FeedbackRequest
import com.timelysoft.tsjdomcom.utils.MyUtils
import kotlinx.android.synthetic.main.fragment_send_feedback.*

class SendFeedbackFragment : Fragment() {
    private lateinit var viewModel: FeedbackViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.show()
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(FeedbackViewModel::class.java)

        return inflater.inflate(R.layout.fragment_send_feedback, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHint()
        if (AppPreferences.isLogined){
            feedback_email.setText(AppPreferences.email.toString())
            feedback_email.tag = feedback_email.keyListener
            feedback_email.keyListener = null
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.feedback_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.send_feedback -> {
                senFeedBack()
            }
        }
        return super.onOptionsItemSelected(item)

    }

    private fun senFeedBack() {

        MyUtils.hideKeyboard(activity!!, view!!)
        if (isValid()) {
            MainActivity.alert.show()
            val body = FeedbackRequest(
                feedbact_name.text.toString(),  feedback_email.text.toString(), feedback_message.text.toString()
            )

            viewModel.sendFeedbackN(body).observe(this, Observer { result ->
                val msg = result.msg
                MainActivity.alert.hide()
                when(result.status){
                    Status.SUCCESS ->{
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                    Status.ERROR, Status.NETWORK ->{
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun isValid(): Boolean {
        var valid = true
        if (feedbact_name.text.toString().isEmpty()) {
            feedbact_name_out.error = "Поле не должно быть пустым"
            valid = false
        } else {
            feedbact_name_out.isErrorEnabled = false
        }

        if (feedback_message.text.toString().isEmpty()) {
            feedback_message_out.error = "Поле не должно быть пустым"
            valid = false
        } else {
            feedback_message_out.isErrorEnabled = false
        }

        if (!AppPreferences.isLogined) {
            if (!MyUtils.emailValidate(feedback_email.text.toString())) {
                valid = false
                feedback_email_out.error = "Не правильный email"
            } else {
                feedback_email_out.isErrorEnabled = false
            }
        }
        return valid
    }

    override fun onStart() {
        super.onStart()
        intColor()
    }

    private fun initHint() {
        feedback_email_out.defaultHintTextColor =
                ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
    }

    private fun intColor() {
        feedbact_name.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && feedbact_name.text!!.isNotEmpty()) {
                feedbact_name_out.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            }
        }

        feedback_email.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && feedback_email.text!!.isNotEmpty()) {
                feedback_email_out.defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            }
        }

        feedback_message.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && feedback_message.text!!.isNotEmpty()) {
                feedback_message_out

                    .defaultHintTextColor =
                    ColorStateList.valueOf(resources.getColor(R.color.colorAccent))
            }
        }
    }
}
