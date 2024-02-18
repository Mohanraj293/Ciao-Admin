package com.lazymohan.ciaoadmin.details

import com.lazymohan.ciaoadmin.Booking
import com.lazymohan.ciaoadmin.details.Status.LOADING

data class BookingState(
  val status : Status = LOADING,
  val booking: Booking? = null,
  val errorMsg: String? = null
)
