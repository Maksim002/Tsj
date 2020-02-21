package com.example.tsj.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BookingRequest {
    private var checkInDateTime = "0000/00/00"
    private var checkOutDateTime = "0000/00/00"

    fun setCheckInDateTime(checkInDateTime: String) {
        this.checkInDateTime = checkInDateTime
    }

    fun getCheckInDateTime(): String {
        return checkInDateTime
    }
}