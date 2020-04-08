package com.timelysoft.tsjdomcom.ui.feedback


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.timelysoft.tsjdomcom.R
import kotlinx.android.synthetic.main.fragment_feedback.*


class FeedbackFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.show()
        return inflater.inflate(
            R.layout.fragment_feedback,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        feedback_send_message.setOnClickListener { findNavController().navigate(R.id.navigation_send_feedback) }
        initViews()
    }

    private fun initViews() {
        feedback_phone_container.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+996312979239")
            startActivity(intent)
        }
    }
}
