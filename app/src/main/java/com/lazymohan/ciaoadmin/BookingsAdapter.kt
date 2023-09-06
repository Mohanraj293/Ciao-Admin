package com.lazymohan.ciaoadmin

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.lazymohan.ciaoadmin.databinding.BookingItemBinding

class BookingsAdapter(
  private val context: Context,
  val listener: ClickListener
) : RecyclerView.Adapter<BookingsAdapter.BookingViewHolder>() {
  private val bookingList = ArrayList<Booking>()
  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int,
  ): BookingViewHolder {
    val binding = BookingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return BookingViewHolder(binding)
  }

  override fun getItemCount() = bookingList.size

  override fun onBindViewHolder(
    holder: BookingViewHolder,
    position: Int,
  ) {
    holder.setData(bookingList[position])
  }

  inner class BookingViewHolder(private val binding: BookingItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n") fun setData(item: Booking) {
      binding.name.text = item.name
      binding.from.text = "From: ${item.from}"
      binding.to.text = "To: ${item.to}"
      binding.pickDate.text = "PickDate :${item.pickUpDate} ${item.pickUptime}"
      binding.root.setOnClickListener {
        listener.onItemClick(item)
      }
      if (item.takeRide == 0) {
        binding.tvChip.text = "Not Taken"
        binding.tvChip.background = ActivityCompat.getDrawable(context, R.drawable.chip_rounded_grey)
      }
      if (item.isPickupCompleted) {
        binding.tvChip.text = "Completed"
        binding.tvChip.background = ActivityCompat.getDrawable(context, R.drawable.chip_rounded_green)
      }
      if (item.takeRide == 1) {
        binding.tvChip.text = "In progress"
        binding.tvChip.background = ActivityCompat.getDrawable(context, R.drawable.chip_rounded_yellow)
      }
    }
  }

  @SuppressLint("NotifyDataSetChanged")
  fun setData(bookings: List<Booking>) {
    bookingList.clear()
    bookingList.addAll(bookings)
    notifyDataSetChanged()
  }
}

interface ClickListener {
  fun onItemClick(item: Booking)
}