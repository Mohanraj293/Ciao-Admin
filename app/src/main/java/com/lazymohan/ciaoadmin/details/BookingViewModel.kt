package com.lazymohan.ciaoadmin.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.lazymohan.ciaoadmin.Booking
import com.lazymohan.ciaoadmin.details.Status.ERROR
import com.lazymohan.ciaoadmin.details.Status.LOADING
import com.lazymohan.ciaoadmin.details.Status.SUCCESS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by Mohanraj R on 19/08/23.
 */
class BookingViewModel : ViewModel() {

  private var _bookingValue = MutableLiveData<BookingState>()
  val booking: LiveData<BookingState> = _bookingValue

  private val databaseReference = Firebase.database.reference

  init {
    _bookingValue.value = BookingState(status = LOADING)
  }

  fun readData(id: String) {
    viewModelScope.launch {
      delay(500)
      databaseReference.child("bookings").child(id).get()
        .addOnSuccessListener {
          _bookingValue.value = BookingState(
            status = SUCCESS,
            booking = it.getValue(Booking::class.java)
          )
        }
        .addOnFailureListener {
          _bookingValue.value = BookingState(
            status = ERROR,
            errorMsg = it.message
          )
        }
    }
  }

  fun takeRide(id: String, driverName: String, driverNum: String) {
    viewModelScope.launch(Dispatchers.IO) {
      val booking = _bookingValue.value!!.booking
      if (booking != null) {
        databaseReference.updateChildren(
          mutableMapOf(
            "/bookings/$id" to Booking(
              createdAt = booking.createdAt,
              from = booking.from,
              id = id,
              isPickupCompleted = booking.isPickupCompleted,
              name = booking.name,
              phoneNumber = booking.phoneNumber,
              pickUpDate = booking.pickUpDate,
              pickUptime = booking.pickUptime,
              takeRide = 1,
              to = booking.to,
              vType = booking.vType,
              driverName = driverName,
              driverNum = driverNum
            ).toMap()
          ) as Map<String, Any>
        )
      }
    }
  }

  fun completeRide(id: String) {
    viewModelScope.launch(Dispatchers.IO) {
      val booking = _bookingValue.value!!.booking
      if (booking != null) {
        databaseReference.updateChildren(
          mutableMapOf(
            "/bookings/$id" to Booking(
              createdAt = booking.createdAt,
              from = booking.from,
              id = id,
              isPickupCompleted = true,
              name = booking.name,
              phoneNumber = booking.phoneNumber,
              pickUpDate = booking.pickUpDate,
              pickUptime = booking.pickUptime,
              takeRide = 2,
              to = booking.to,
              vType = booking.vType,
              driverName = booking.driverName,
              driverNum = booking.driverNum
            ).toMap()
          ) as Map<String, Any>
        )
      }
    }
  }
}