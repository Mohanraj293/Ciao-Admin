package com.lazymohan.ciaoadmin.dummy

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tarkalabs.tarkaui.components.HorizontalSpacer
import com.tarkalabs.tarkaui.components.TUIMediaThumbnail
import com.tarkalabs.tarkaui.components.TUIMediaThumbnailType
import com.tarkalabs.tarkaui.components.VerticalSpacer
import com.tarkalabs.tarkaui.components.base.IconButtonSize
import com.tarkalabs.tarkaui.components.base.IconButtonStyle
import com.tarkalabs.tarkaui.components.base.TUIIconButton
import com.tarkalabs.tarkaui.icons.ArrowDown24
import com.tarkalabs.tarkaui.icons.Delete24
import com.tarkalabs.tarkaui.icons.TarkaIcons
import com.tarkalabs.tarkaui.theme.TUITheme


@Composable
fun AttachmentCard(
    modifier: Modifier = Modifier,
    type: TUIMediaThumbnailType,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = TUITheme.colors.surface),
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TUIMediaThumbnail(type = type, showTrailingIcon = false)
            HorizontalSpacer(space = 8)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "document.jpg", style = TUITheme.typography.body7,
                    color = TUITheme.colors.onSurface
                )
                VerticalSpacer(space = 8)
                Text(
                    text = "6 Aug, 2022 02:39PM", style = TUITheme.typography.body8,
                    color = TUITheme.colors.inputText
                )
            }
            AnimatedVisibility(visible = false) {
                TUIIconButton(
                    icon = TarkaIcons.Regular.ArrowDown24,
                    buttonSize = IconButtonSize.L,
                    iconButtonStyle = IconButtonStyle.GHOST,
                    onIconClick = {}
                )
            }
            TUIIconButton(
                icon = TarkaIcons.Regular.Delete24,
                buttonSize = IconButtonSize.L,
                iconButtonStyle = IconButtonStyle.GHOST,
                onIconClick = {
                    onDeleteClick.invoke()
                }
            )
        }
    }
}

@Preview
@Composable
fun Prev() {
    TUITheme {
        AttachmentCard(
            modifier = Modifier,
            type = TUIMediaThumbnailType.Audio,
            onDeleteClick = {

            }
        )
    }
}