package com.example.slicingbcf.implementation.peserta.pusat_informasi

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.slicingbcf.R
import com.example.slicingbcf.constant.ColorPalette
import com.example.slicingbcf.constant.StyledText
import com.example.slicingbcf.data.local.DataPusatInformasi
import com.example.slicingbcf.implementation.mentor.forum_diskusi.ForumDiskusiEvent
import com.example.slicingbcf.implementation.mentor.forum_diskusi.ForumDiskusiViewModel
import com.example.slicingbcf.ui.animations.SubmitLoadingIndicatorDouble
import com.example.slicingbcf.ui.scaffold.DiscussionScaffold
import com.example.slicingbcf.ui.shared.pusat_informasi.PusatInformasiItem
import com.example.slicingbcf.ui.shared.textfield.OutlineTextFieldComment
import com.example.slicingbcf.ui.shared.textfield.SearchBarCustom
import com.example.slicingbcf.util.parseDate

@Composable
fun PusatInformasiScreen(
  modifier : Modifier,
  onNavigateDetailPusatInformasi : (String) -> Unit,
  onNavigateSearchForumDiskusi : () -> Unit,
  viewModel : ForumDiskusiViewModel = hiltViewModel()
) {
  val state by viewModel.state.collectAsState()

  val scrollState = rememberScrollState()

  val addData = { newMockDataPusatInformasi : DataPusatInformasi ->
    viewModel.onEvent(ForumDiskusiEvent.AddPertanyaan(newMockDataPusatInformasi))
  }

  DiscussionScaffold(
    onClick = { Log.d("discussion", "discussion") }
  ) { innerPadding ->
    Column(
      modifier = modifier
        .padding(innerPadding)
        .statusBarsPadding()
        .padding(
          horizontal = 16.dp,
        )
        .verticalScroll(scrollState),
      verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
      TopSection(
        addData = addData,
        pertanyaan = state.pertanyaan,
        onChangePertanyaan = { viewModel.onEvent(ForumDiskusiEvent.PertanyaanChanged(it)) },
        onNavigateSearchForumDiskusi = onNavigateSearchForumDiskusi
      )
      BottomSection(
        onNavigateDetailForumDiskusi = onNavigateDetailPusatInformasi,
        mutableListMockDataPusatInformasi = state.dataList
      )
    }

    SubmitLoadingIndicatorDouble(
      isLoading = state.isLoading,
      title = "Memproses pertanyaan...",
      onAnimationFinished = {
        viewModel.onEvent(ForumDiskusiEvent.ClearState)
      },
      titleBerhasil = "Pertanyaan berhasil ditambahkan!"
    )
  }
}

@Composable
private fun BottomSection(
  onNavigateDetailForumDiskusi : (String) -> Unit,
  mutableListMockDataPusatInformasi : List<DataPusatInformasi>
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(24.dp)
  ) {
    mutableListMockDataPusatInformasi.forEach {
      PusatInformasiItem(
        data = it,
        onClick = { onNavigateDetailForumDiskusi(it.username ?: "") }
      )
    }
  }

}

@Composable
private fun TopSection(
  addData : (DataPusatInformasi) -> Unit,
  pertanyaan : String,
  onChangePertanyaan : (String) -> Unit,
  onNavigateSearchForumDiskusi : () -> Unit
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
        modifier = Modifier
          .fillMaxWidth()
          .clickable {

          },
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        SearchBarCustom(
          onSearch = { Log.d("search", it) },
          modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
          color = ColorPalette.PrimaryColor700,
          textStyle = StyledText.MobileSmallMedium,
          title = "Cari Pertanyaan",
          isEnable = false,
          onClick = {
            onNavigateSearchForumDiskusi()
          }
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
      OutlineTextFieldComment(
        value = pertanyaan,
        onValueChange = {
          onChangePertanyaan(it)
        },
        onSubmit = {
          addData(
            DataPusatInformasi(
              profilePicture = R.drawable.ic_launcher_background,
              username = "Guest",
              question = pertanyaan,
              timestamp = parseDate("1 days ago"),
            )
          )
        },
        label = "Buka Obrolan...",
        isEnabled = true
      )
    }
    HorizontalDivider(
      modifier = Modifier.fillMaxWidth()
    )
  }
}

