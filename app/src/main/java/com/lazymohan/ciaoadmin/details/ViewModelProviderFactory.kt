package com.lazymohan.ciaoadmin.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Mohanraj R on 04/09/23.
 */
class ViewModelProviderFactory(val id: String): ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(BookingViewModel::class.java)) {
      return BookingViewModel(id) as T
    }
    throw IllegalArgumentException("Unknown viewmodel class")
  }
}