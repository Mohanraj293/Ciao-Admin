package com.lazymohan.ciaoadmin.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.tarkalabs.tarkaui.theme.TUITheme

class BookingDetailsActivity : ComponentActivity() {

  private val viewModel: BookingViewModel by viewModels()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      TUITheme {
        BookingDetailsScreenContent(
          uiState = viewModel.uiState.collectAsState().value,
          onBackPressed = { onBackPressedDispatcher.onBackPressed() }
        )
      }
    }
  }
}
