package com.lazymohan.ciaoadmin.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.lazymohan.ciaoadmin.Booking
import com.lazymohan.ciaoadmin.details.BookingEvent.LoadDetails
import com.lazymohan.ciaoadmin.details.Status.ERROR
import com.lazymohan.ciaoadmin.details.Status.SUCCESS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * Created by Mohanraj R on 19/08/23.
 */
class BookingViewModel(private val id: String) : ViewModel() {

  private var _bookingValue = MutableLiveData<Booking>()
  val booking: LiveData<Booking> = _bookingValue

  private val databaseReference = Firebase.database.reference

  fun readData(){
    databaseReference.child("bookings").child(id).get()
      .addOnSuccessListener {
        _bookingValue.value = it.getValue(Booking::class.java)
      }
      .addOnFailureListener {

      }
  }
}