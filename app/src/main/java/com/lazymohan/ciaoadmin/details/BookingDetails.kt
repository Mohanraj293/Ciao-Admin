package com.lazymohan.ciaoadmin.details

import android.annotation.SuppressLint
import android.app.AlertDialog.Builder
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.lazymohan.ciaoadmin.Booking
import com.lazymohan.ciaoadmin.R
import com.lazymohan.ciaoadmin.databinding.ActivityBookingDetailsBinding
import com.lazymohan.ciaoadmin.databinding.AlertEditTextBinding
import com.lazymohan.ciaoadmin.details.Status.ERROR
import com.lazymohan.ciaoadmin.details.Status.LOADING
import com.lazymohan.ciaoadmin.details.Status.SUCCESS

class BookingDetails : AppCompatActivity() {

  private lateinit var binding: ActivityBookingDetailsBinding
  private val bookingViewModel: BookingViewModel by viewModels()
  private var id: String? = null
  private var bookingState: BookingState? = null

  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (intent.hasExtra("id"))
      id = intent.getStringExtra("id").toString()
    bookingViewModel.readData(id!!)
    window.statusBarColor = ContextCompat.getColor(this, R.color.toolbar)
    binding = ActivityBookingDetailsBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.toolbar.title = "Pickup Details"
    bookingViewModel.uiState.value.let {
      when (it.status) {
        LOADING -> {
          binding.progressBar.isVisible = true
        }

        SUCCESS -> {
          binding.progressBar.isVisible = false
          this.bookingState = it
          setData(it.booking)
        }

        ERROR -> {
          Snackbar.make(
            binding.root, "Error -> ${it.errorMsg}", Snackbar.LENGTH_SHORT
          ).show()
          binding.progressBar.isVisible = false
        }
      }
    }
  }

  @SuppressLint("SetTextI18n")
  private fun setData(booking: Booking?) {
    binding.apply {
      user.isVisible = true
      inprgBtn.isVisible = true

      name.text = "Name: ${booking?.name}"
      from.text = "From: ${booking?.from}"
      to.text = "To: ${booking?.to}"
      pickDate.text = "Pickup date: ${booking?.pickUpDate}"
      pickTime.text = "Pickup time: ${booking?.pickUptime}"
      vType.text = "Vehicle Type: ${booking?.vType}"
      phoneNumber.text = "Phone Number: ${booking?.phoneNumber}"

      if (!booking?.driverNum.isNullOrEmpty() || !booking?.driverName.isNullOrEmpty()) {
        driver.isVisible = true
        driverName.isVisible = true
        driverNum.isVisible = true
        driverName.text = "Driver Name: ${booking?.driverName}"
        driverNum.text = "Driver Number: ${booking?.driverNum}"
      } else {
        driverName.isVisible = false
        driverNum.isVisible = false
        driver.isVisible = false
      }
      when (booking?.takeRide) {
        0 -> {
          inprgBtn.text = "Take Ride"
        }

        1 -> {
          inprgBtn.text = "Complete Ride"
        }

        else -> {
          inprgBtn.isVisible = false
        }
      }
      inprgBtn.setOnClickListener {
        if (bookingState?.booking?.takeRide == 0) {
          val editTextBinding = AlertEditTextBinding.inflate(layoutInflater)
          val alertDialog = Builder(this@BookingDetails)
            .setTitle("Enter Driver Details")
            .setPositiveButton("Take Ride", null)
            .setNegativeButton("Cancel", null)
            .create()
          alertDialog.setOnShowListener {
            val submitButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            submitButton.setOnClickListener {
              if (editTextBinding.etDriverName.text.isNullOrEmpty() || editTextBinding.etPhoneNumber.text.isNullOrEmpty()) {
                Toast.makeText(
                  this@BookingDetails,
                  "Please enter the details",
                  Toast.LENGTH_SHORT
                ).show()
              } else {
                bookingViewModel.takeRide(
                  id = id!!,
                  driverName = editTextBinding.etDriverName.text.toString(),
                  driverNum = editTextBinding.etPhoneNumber.text.toString()
                )
                bookingViewModel.readData(id!!)
                binding.inprgBtn.text = "Complete ride"
                Toast.makeText(
                  this@BookingDetails,
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
          bookingViewModel.completeRide(id = id!!)
          bookingViewModel.readData(id!!)
          finish()
        }
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