package com.lazymohan.ciaoadmin.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.lazymohan.ciaoadmin.details.Status.SUCCESS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * Created by Mohanraj R on 19/08/23.
 */
class BookingViewModel(private val id: String): ViewModel() {

  private val bookingState = MutableStateFlow(BookingState())
  private lateinit var database: DatabaseReference


  fun loadDetails() {
    bookingState.value = bookingState.value.copy(
      status = SUCCESS,
    )
    viewModelScope.launch {
      bookingState.value = bookingState.value.copy(
      )
    }
  }

  fun getBookingDetails() {
    database = Firebase.database.reference
    database.child("bookings/${id}")
  }
}