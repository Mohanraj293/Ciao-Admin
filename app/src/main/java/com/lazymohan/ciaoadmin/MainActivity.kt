package com.lazymohan.ciaoadmin

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle.State.RESUMED
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.lazymohan.ciaoadmin.databinding.ActivityMainBinding
import com.lazymohan.ciaoadmin.details.BookingDetails.Companion.getCallingIntent

class MainActivity : AppCompatActivity(), ClickListener, ValueEventListener, MenuProvider {

  private lateinit var database: DatabaseReference
  private var bookingsList = arrayListOf<Booking>()
  private lateinit var binding: ActivityMainBinding
  private lateinit var adapter: BookingsAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    window.statusBarColor = ContextCompat.getColor(this, R.color.toolbar)
    binding.toolbar.title = "Ciao Admin"
    addMenuProvider(this, this, RESUMED)
    adapter = BookingsAdapter(this, this)
    binding.rv.layoutManager = LinearLayoutManager(this)
    binding.rv.adapter = adapter
    database = Firebase.database.reference
    database.child("bookings").addValueEventListener(this)
  }

  override fun onCreateMenu(
    menu: Menu,
    menuInflater: MenuInflater,
  ) {
    menuInflater.inflate(R.menu.filter_menu, menu)
  }

  override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
    return when (menuItem.itemId) {
      R.id.filter -> {
        true
      }

      else -> false
    }
  }

  override fun onItemClick(item: Booking) {
    startActivity(
      getCallingIntent(
        context = this,
        id = item.id,
        name = item.name,
        from = item.from,
        to = item.to,
        pickDate = item.pickUpDate,
        isPickupCompleted = item.isPickupCompleted,
        pickTime = item.pickUptime,
        vType = item.vType,
        phoneNumber = item.phoneNumber,
        takeRide = item.takeRide,
        createdAt = item.createdAt
      )
    )
  }

  override fun onDataChange(snapshot: DataSnapshot) {
    if (snapshot.exists()) {
      bookingsList.clear()
      for (bookingSnapShot in snapshot.children) {
        val booking = bookingSnapShot.getValue(Booking::class.java)
        if (booking != null) {
          bookingsList.add(booking)
        } else {
          bookingsList.clear()
        }
      }
      adapter.setData(bookingsList)
    }
  }

  override fun onCancelled(error: DatabaseError) {
    Snackbar.make(binding.root, "Something went wrong..!", Snackbar.LENGTH_LONG).show()
  }
}