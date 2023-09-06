package com.lazymohan.ciaoadmin.details

/**
 * Created by Mohanraj R on 04/09/23.
 */
sealed class BookingEvent {
  object LoadDetails: BookingEvent()

}