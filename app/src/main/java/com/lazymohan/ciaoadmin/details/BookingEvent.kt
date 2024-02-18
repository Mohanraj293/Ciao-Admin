package com.lazymohan.ciaoadmin.details

sealed class BookingEvent {
  object LoadDetails: BookingEvent()
}