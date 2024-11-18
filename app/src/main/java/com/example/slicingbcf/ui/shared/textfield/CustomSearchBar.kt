package com.example.slicingbcf.ui.shared.textfield

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.slicingbcf.constant.ColorPalette
import com.example.slicingbcf.constant.StyledText


@Composable
fun SearchBarCustom(
  onSearch : (String) -> Unit,
  modifier : Modifier = Modifier,
  textStyle : TextStyle = StyledText.MobileSmallRegular,
  color : Color = ColorPalette.Monochrome300
) {
  var query by remember { mutableStateOf("") }

  TextField(
    textStyle = StyledText.MobileSmallRegular,
    modifier = modifier,

    value = query,
    onValueChange = {
      query = it
      onSearch(it)
    },

    placeholder = {
      Text(
        "Cari Pertanyaan",
        style = textStyle,
        color = color
      )
    },
    leadingIcon = {
      Icon(
        Icons.Default.Search,
        contentDescription = "Search Icon",
        modifier = Modifier.size(20.dp),
        tint = color
      )
    },
    singleLine = true,
    shape = RoundedCornerShape(40.dp),
    colors = TextFieldDefaults.colors(
      // ! biar ga ada default border bottom
      focusedIndicatorColor = Color.Transparent,
      unfocusedIndicatorColor = Color.Transparent,
      disabledIndicatorColor = Color.Transparent,
      errorIndicatorColor = Color.Transparent,
      unfocusedContainerColor = ColorPalette.PrimaryColor100,
      focusedContainerColor = ColorPalette.PrimaryColor100,
    )

  )
}
