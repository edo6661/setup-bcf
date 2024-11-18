package com.example.slicingbcf.ui.shared.pusat_informasi

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.slicingbcf.R
import com.example.slicingbcf.constant.ColorPalette
import com.example.slicingbcf.constant.StyledText
import com.example.slicingbcf.ui.shared.textfield.CustomOutlinedTextField


@Composable
fun PusatInformasiContent(
  data : DataPusatInformasi,
  isCommentable : Boolean = true,
  isEnabledTextField : Boolean = false
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    Row(
      horizontalArrangement = Arrangement.spacedBy(16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Image(
        painter = painterResource(id = data.profilePicture ?: R.drawable.ic_launcher_background),
        contentDescription = "Profile Picture",
        modifier = Modifier
          .size(40.dp)
          .clip(RoundedCornerShape(50)),
        contentScale = ContentScale.Crop,
      )
      Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.weight(1f)
      ) {
        Text(
          text = "Guest",
          style = StyledText.MobileBaseMedium,
          color = ColorPalette.OnSurface
        )
        Text(
          text = "Now",
          style = StyledText.MobileXsRegular,
          color = ColorPalette.Monochrome400
        )
      }
      IconButton(
        onClick = { /*TODO*/ },
        modifier = Modifier.size(64.dp)
      ) {
        Image(
          painter = painterResource(id = R.drawable.icon_heart),
          contentDescription = "More",
          modifier = Modifier.size(24.dp),
        )
      }

    }
    Text(
      text = data.question,
      style = StyledText.MobileBaseRegular,
      color = ColorPalette.OnSurfaceVariant
    )
    // TODO: kalo isEnabledTextField nya itu true, kalo di klik bakal muncul button batal dan kirim, more spesifically di figma prototype liat aja sendiri
    if (isCommentable) {
      CustomOutlinedTextField(
        value = "",
        onValueChange = {},
        label = "Tambah komentar",
        placeholder = "Tambah komentar",
        rounded = 80,
        labelDefaultColor = ColorPalette.Monochrome300,
        labelFocusedColor = ColorPalette.Monochrome500,

        modifier = Modifier.fillMaxWidth(),
        isEnabled = isEnabledTextField,
        borderColor = ColorPalette.Outline,

        )
    }
  }
}

data class DataPusatInformasi(
  val username : String?,
  val profilePicture : Int?,
  val date : String,
  val question : String,
)


val mockDataPusatInformasi = listOf(
  DataPusatInformasi(
    username = "John Doe",
    profilePicture = null,
    date = "2 days ago",
    question = "Bagaimana cara membuat pitch deck yang menarik?"
  ),
  DataPusatInformasi(
    username = "Jane Doe",
    profilePicture = R.drawable.avatar,
    date = "3 days ago",
    question = "Apa saja yang harus dipersiapkan sebelum membuat pitch deck?"
  ),
)
