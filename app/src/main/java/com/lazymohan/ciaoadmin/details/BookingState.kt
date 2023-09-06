package com.lazymohan.ciaoadmin.details

import com.lazymohan.ciaoadmin.Booking
import com.lazymohan.ciaoadmin.details.Status.LOADING

/**
 * Created by Mohanraj R on 19/08/23.
 */
data class BookingState(
  val status : Status = LOADING,
  val booking: Booking? = null,
  val errorMsg: String? = null
)
