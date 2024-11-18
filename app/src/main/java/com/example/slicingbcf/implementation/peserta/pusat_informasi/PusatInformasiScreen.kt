package com.example.slicingbcf.implementation.peserta.pusat_informasi

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.slicingbcf.R
import com.example.slicingbcf.constant.ColorPalette
import com.example.slicingbcf.constant.StyledText
import com.example.slicingbcf.ui.shared.textfield.CustomOutlinedTextField
import com.example.slicingbcf.ui.shared.textfield.SearchBarCustom

@Composable
fun PusatInformasiScreen(
  modifier : Modifier,
  onNavigateDetailPusatInformasi : (String) -> Unit
) {
  val scrollState = rememberScrollState()

  Column(
    modifier = modifier
      .statusBarsPadding()
      .padding(
        horizontal = 16.dp,
      )
      .verticalScroll(scrollState),
    verticalArrangement = Arrangement.spacedBy(28.dp)
  ) {
    TopSection()
    BottomSection(
      onNavigateDetailPusatInformasi = onNavigateDetailPusatInformasi
    )
  }
}

@Composable
private fun BottomSection(
  onNavigateDetailPusatInformasi : (String) -> Unit
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(24.dp)
  ) {
    mockDataPusatInformasi.forEach {
      PusatInformasiItem(
        data = it,
        onClick = { onNavigateDetailPusatInformasi(it.username ?: "") }
      )
    }
  }

}

@Composable
fun TopSection(
) {
  Column(
    modifier = Modifier
      .fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(36.dp)
  ) {
    Text(
      text = "Forum Diskusi",
      style = StyledText.MobileLargeSemibold,
      textAlign = TextAlign.Center,
      modifier = Modifier.fillMaxWidth()
    )

    Column(
      verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        SearchBarCustom(
          onSearch = { Log.d("search", it) },
          modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
          color = ColorPalette.PrimaryColor700,
          textStyle = StyledText.MobileSmallMedium
        )

        SmallFloatingActionButton(
          onClick = { Log.d("filter", "filter clicked") },
          modifier = Modifier.size(40.dp),
          containerColor = ColorPalette.PrimaryColor100
        ) {
          Image(
            painter = painterResource(id = R.drawable.filter),
            contentDescription = "Filter",
            modifier = Modifier.size(20.dp)
          )
        }
      }
      Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        Text(
          text = "Butuh diskusi? ",
          style = StyledText.MobileMediumSemibold,
          color = ColorPalette.PrimaryColor700,
        )
        Text(
          text = "Ajukan pertanyaan dan buka forum untuk memulai obrolan!",
          style = StyledText.MobileBaseRegular,
          color = ColorPalette.PrimaryColor700,
        )
      }
      CustomOutlinedTextField(
        value = "",
        onValueChange = {},
        label = "Tulis pertanyaanmu disini",
        placeholder = "Tulis pertanyaanmu disini",
        modifier = Modifier.fillMaxWidth(),
        borderColor = ColorPalette.Monochrome400,
        labelDefaultColor = ColorPalette.Monochrome400,
      )
    }
    HorizontalDivider(
      modifier = Modifier.fillMaxWidth()
    )
  }
}


@Composable
private fun PusatInformasiItem(
  data : DataPusatInformasi,
  onClick : () -> Unit
) {
  Column(
    modifier = Modifier
      .clip(
        shape = RoundedCornerShape(32.dp)
      )
      .clickable(onClick = onClick)
      .background(ColorPalette.Monochrome100)
      .padding(
        horizontal = 16.dp,
        vertical = 20.dp
      )
      .fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(32.dp)
  ) {
    TopSectionPusatInformasiItem(data)
  }
}


@Composable
private fun TopSectionPusatInformasiItem(
  data : DataPusatInformasi
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
    CustomOutlinedTextField(
      value = "",
      onValueChange = {},
      label = "Tambah komentar",
      placeholder = "Tambah komentar",
      rounded = 80,
      labelDefaultColor = ColorPalette.Monochrome400,
      modifier = Modifier.fillMaxWidth(),
      isEnabled = false,
      borderColor = ColorPalette.Outline
    )
  }
}

data class DataPusatInformasi(
  val username : String?,
  val profilePicture : Int?,
  val date : String,
  val question : String,
)


private val mockDataPusatInformasi = listOf(
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

