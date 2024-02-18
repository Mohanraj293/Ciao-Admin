package com.lazymohan.ciaoadmin.details

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lazymohan.ciaoadmin.utlis.extension.getInitials
import com.tarkalabs.tarkaui.components.TUIAppTopBar
import com.tarkalabs.tarkaui.components.VerticalSpacer
import com.tarkalabs.tarkaui.components.base.AvatarSize
import com.tarkalabs.tarkaui.components.base.AvatarType
import com.tarkalabs.tarkaui.components.base.TUIAvatar
import com.tarkalabs.tarkaui.icons.ChevronLeft24
import com.tarkalabs.tarkaui.icons.TarkaIcons
import com.tarkalabs.tarkaui.theme.TUITheme

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BookingDetailsScreenContent(
  uiState: BookingState = BookingState(),
  onBackPressed: () -> Unit
) {
  val scope = rememberCoroutineScope()
  val bottomSheetState = rememberModalBottomSheetState(
    initialValue = ModalBottomSheetValue.Hidden,
    skipHalfExpanded = true
  )
  ModalBottomSheetLayout(
    sheetState = bottomSheetState,
    sheetContent = {

    }) {
    Scaffold(
      topBar = {
        TUIAppTopBar(
          title = "Pickup Details",
          navigationIcon = TarkaIcons.Regular.ChevronLeft24.copy(
            contentDescription = "Back Navigation"
          ),
          onNavigationIconClick = { onBackPressed() }
        )
      }
    ) {
      Column(
        modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .background(color = TUITheme.colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        VerticalSpacer(space = 24)
        TUIAvatar(
          avatarType = AvatarType.Text(text = uiState.booking?.name.getInitials()),
          avatarSize = AvatarSize.XXL,
        )
        VerticalSpacer(space = 24)
        uiState.booking?.name?.let { it1 ->
          Text(
            text = it1,
            style = TUITheme.typography.heading6,
            color = TUITheme.colors.onSurface
          )
        }
        VerticalSpacer(space = 8)
        uiState.booking?.phoneNumber?.let { it1 ->
          Text(
            text = it1,
            style = TUITheme.typography.body7,
            color = TUITheme.colors.utilityOutline
          )
        }
      }
    }
  }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BookingDetailsPreview() {
  TUITheme {
    BookingDetailsScreenContent(onBackPressed = {})
  }
}