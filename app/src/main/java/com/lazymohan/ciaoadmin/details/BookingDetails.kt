package com.lazymohan.ciaoadmin.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle.State.RESUMED
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.lazymohan.ciaoadmin.Booking
import com.lazymohan.ciaoadmin.R
import com.lazymohan.ciaoadmin.databinding.ActivityBookingDetailsBinding

class BookingDetails : AppCompatActivity() {

  private lateinit var binding: ActivityBookingDetailsBinding
  private lateinit var database: DatabaseReference

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

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    database = Firebase.database.reference
    window.statusBarColor = ContextCompat.getColor(this, R.color.toolbar)
    binding = ActivityBookingDetailsBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.toolbar.title = "User Details"
    validateIntent()
    binding.name.text = name
    binding.from.text = from
    binding.to.text = to
    binding.pickDate.text = pickDate
    binding.vType.text = vType
    binding.phoneNumber.text = phoneNumber
    if (takeRide == 0) {
      binding.inprgBtn.text = "Take Ride"
    } else if (takeRide == 1) {
      binding.inprgBtn.text = "Complete Ride"
    } else {
      binding.inprgBtn.isVisible = false
    }
    binding.inprgBtn.setOnClickListener {
      if (takeRide == 0) {
        database.updateChildren(
          mutableMapOf(
            "/bookings/$id" to Booking(
              createdAt = createdAt,
              from = from,
              id = id,
              isPickupCompleted = isPickupCompleted,
              name = name,
              phoneNumber = phoneNumber,
              pickUpDate = pickDate,
              pickUptime = pickTime,
              takeRide = 1,
              to = to,
              vType = vType,
            ).toMap()
          ) as Map<String, Any>
        )
        binding.inprgBtn.text = "Complete ride"
        Toast.makeText(
          this,
          "Ride Started..!",
          Toast.LENGTH_SHORT
        ).show()
      } else {
        database.updateChildren(
          mutableMapOf(
            "/bookings/$id" to Booking(
              createdAt = createdAt,
              from = from,
              id = id,
              isPickupCompleted = true,
              name = name,
              phoneNumber = phoneNumber,
              pickUpDate = pickDate,
              pickUptime = pickTime,
              takeRide = 2,
              to = to,
              vType = vType,
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
      id: String?,
      name: String?,
      from: String?,
      to: String?,
      pickDate: String?,
      isPickupCompleted: Boolean,
      pickTime: String?,
      vType: String?,
      phoneNumber: String?,
      takeRide: Int,
      createdAt: String?,
    ): Intent = Intent(context, BookingDetails::class.java).apply {
      putExtra("id", id)
      putExtra("name", name)
      putExtra("from", from)
      putExtra("to", to)
      putExtra("isPickupCompleted", isPickupCompleted)
      putExtra("pickDate", pickDate)
      putExtra("pickTime", pickTime)
      putExtra("vType", vType)
      putExtra("number", phoneNumber)
      putExtra("takeRide", takeRide)
      putExtra("createdAt", createdAt)
    }
  }

  private fun validateIntent() {
    if (intent.hasExtra("id"))
      id = intent.getStringExtra("id")
    if (intent.hasExtra("isPickupCompleted"))
      isPickupCompleted = intent.getBooleanExtra("isPickupCompleted", false)
    if (intent.hasExtra("name"))
      name = intent.getStringExtra("name")
    if (intent.hasExtra("from"))
      from = intent.getStringExtra("from")
    if (intent.hasExtra("to"))
      to = intent.getStringExtra("to")
    if (intent.hasExtra("pickDate"))
      pickDate = intent.getStringExtra("pickDate")
    if (intent.hasExtra("pickTime"))
      pickTime = intent.getStringExtra("pickTime")
    if (intent.hasExtra("vType"))
      vType = intent.getStringExtra("vType")
    if (intent.hasExtra("number"))
      phoneNumber = intent.getStringExtra("number")
    if (intent.hasExtra("takeRide"))
      takeRide = intent.getIntExtra("takeRide", 0)
    if (intent.hasExtra("createdAt"))
      createdAt = intent.getStringExtra("createdAt")
  }
}