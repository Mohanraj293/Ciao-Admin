package com.lazymohan.ciaoadmin.details

import android.annotation.SuppressLint
import android.app.AlertDialog.Builder
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.lazymohan.ciaoadmin.Booking
import com.lazymohan.ciaoadmin.R
import com.lazymohan.ciaoadmin.databinding.ActivityBookingDetailsBinding
import com.lazymohan.ciaoadmin.databinding.AlertEditTextBinding
import com.lazymohan.ciaoadmin.details.BookingEvent.LoadDetails

class BookingDetails : AppCompatActivity() {

  private lateinit var binding: ActivityBookingDetailsBinding
  private lateinit var database: DatabaseReference
  private var booking: Booking? = null
  private lateinit var bookingViewModel: BookingViewModel
  private var id: String? = null
  private var name: String? = null
  private var from: String? = null
  private var to: String? = null
  private var pickDate: String? = null
  private var pickTime: String? = null
  private var vType: String? = null
  private var phoneNumber: String? = null
  private var takeRide: Int = 0
  private var createdAt: String? = null
  private var isPickupCompleted: Boolean = false

  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (intent.hasExtra("id"))
      id = intent.getStringExtra("id").toString()
    val factory = ViewModelProviderFactory(id!!)
    bookingViewModel = ViewModelProvider(this, factory)[BookingViewModel::class.java]
    bookingViewModel.readData()
    window.statusBarColor = ContextCompat.getColor(this, R.color.toolbar)
    binding = ActivityBookingDetailsBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.toolbar.title = "User Details"
    setData(bookingViewModel.booking.value)
  }

  private fun setData(booking: Booking?) {
    binding.name.text = booking?.name
    binding.from.text = "From: ${booking?.from}"
    binding.to.text = "To: ${booking?.to}"
    binding.pickDate.text = "Pickup date: ${booking?.pickUpDate}"
    binding.vType.text = "Vehicle Type: ${booking?.vType}"
    binding.phoneNumber.text = "Phone Number: ${booking?.phoneNumber}"
    when (booking?.takeRide) {
      0 -> {
        binding.inprgBtn.text = "Take Ride"
      }

      1 -> {
        binding.inprgBtn.text = "Complete Ride"
      }

      else -> {
        binding.inprgBtn.isVisible = false
      }
    }
    binding.inprgBtn.setOnClickListener {
      if (booking?.takeRide == 0) {
        val editTextBinding = AlertEditTextBinding.inflate(layoutInflater)
        val alertDialog = Builder(this)
          .setTitle("Enter Driver Details")
          .setPositiveButton("Take Ride", null)
          .setNegativeButton("Cancel", null)
          .create()
        alertDialog.setOnShowListener {
          val alertTitle = alertDialog.findViewById<View>(androidx.appcompat.R.id.title_template)
          binding.root.setPadding(
            alertTitle?.paddingStart ?: 0,
            0,
            alertTitle?.paddingEnd ?: 0,
            0
          )
          val submitButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
          submitButton.setOnClickListener {
            if (editTextBinding.etDriverName.text.isNullOrEmpty() || editTextBinding.etPhoneNumber.text.isNullOrEmpty()) {
              Toast.makeText(
                this,
                "Please enter the details",
                Toast.LENGTH_SHORT
              ).show()
            } else {
              database.updateChildren(
                mutableMapOf(
                  "/bookings/$id" to Booking(
                    createdAt = booking?.createdAt,
                    from = booking?.from,
                    id = id,
                    isPickupCompleted = booking!!.isPickupCompleted,
                    name = booking?.name,
                    phoneNumber = booking?.phoneNumber,
                    pickUpDate = booking?.pickUpDate,
                    pickUptime = booking?.pickUptime,
                    takeRide = 1,
                    to = booking?.to,
                    vType = booking?.vType,
                  ).toMap()
                ) as Map<String, Any>
              )
              binding.inprgBtn.text = "Complete ride"
              Toast.makeText(
                this,
                "Ride Started..!",
                Toast.LENGTH_SHORT
              ).show()
              alertDialog.dismiss()
            }
          }
          val cancelButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
          cancelButton.setOnClickListener { alertDialog.dismiss() }
        }
        alertDialog.setView(editTextBinding.root)
        alertDialog.show()
      } else {
        database.updateChildren(
          mutableMapOf(
            "/bookings/$id" to Booking(
              createdAt = booking?.createdAt,
              from = booking?.from,
              id = id,
              isPickupCompleted = true,
              name = booking?.name,
              phoneNumber = booking?.phoneNumber,
              pickUpDate = booking?.pickUpDate,
              pickUptime = booking?.pickUptime,
              takeRide = 2,
              to = booking?.to,
              vType = booking?.vType,
            ).toMap()
          ) as Map<String, Any>
        )
        finish()
      }
    }
  }

  companion object {
    fun getCallingIntent(
      context: Context,
      id: String,
    ): Intent = Intent(context, BookingDetails::class.java).apply {
      putExtra("id", id)
    }
  }
}