package com.example.tsj.ui.bid.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.tsj.R
import kotlinx.android.synthetic.main.fragment_bid_add.*

class BidAddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.show()

        return inflater.inflate(R.layout.fragment_bid_add, container, false)
    }

    override fun onStart() {
        super.onStart()
        initAutoCompleteTypeBid()
        initAutocompleteAdresBid()
    }

    private fun initAutocompleteAdresBid() {
        val adresOfBid = arrayOf(
            "Южный ветер, 8 мкр, д. 46, кв. 27","7 небо, Тоголок - Молдо, дом. 14")
        val typeAdapter = ArrayAdapter <String> (context!!, R.layout.support_simple_spinner_dropdown_item, adresOfBid)
        bid_add_adres.setAdapter(typeAdapter)
        bid_add_adres.keyListener = null


        bid_add_adres.setOnClickListener {
            bid_add_adres.showDropDown()
        }

        bid_add_adres.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus){
                bid_add_adres.showDropDown()
            }
        }

    }

    private fun initAutoCompleteTypeBid() {
        val typeOfBid = arrayOf(
            "Заявка на ремонт","Вопрос", "Жалоба")
        val typeAdapter = ArrayAdapter <String> (context!!, R.layout.support_simple_spinner_dropdown_item, typeOfBid)
        bid_add_type.setAdapter(typeAdapter)
        bid_add_type.keyListener = null


        bid_add_type.setOnClickListener {
            bid_add_type.showDropDown()
        }

        bid_add_type.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus){
                bid_add_type.showDropDown()
            }
        }

    }

}
