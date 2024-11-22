package com.example.slicingbcf.implementation.auth.registrasi

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.slicingbcf.constant.ColorPalette
import com.example.slicingbcf.constant.StyledText
import com.example.slicingbcf.ui.animations.AnimatedContentSlide
import com.example.slicingbcf.ui.shared.PrimaryButton
import com.example.slicingbcf.ui.shared.textfield.CustomOutlinedTextField
import com.example.slicingbcf.ui.shared.textfield.CustomOutlinedTextFieldDropdown
import com.example.slicingbcf.ui.shared.textfield.CustomOutlinedTextFieldDropdownDate
import com.example.slicingbcf.util.convertMillisToDate

@Composable
fun RegistrasiScreen(modifier : Modifier) {
  var currentScreen by rememberSaveable { mutableIntStateOf(0) }
  var indicatorProgress by remember { mutableFloatStateOf(0.2f) }
  var initialState by remember { mutableIntStateOf(0) }
  val changeScreen : (Int) -> Unit = { screen -> currentScreen = screen }

  val nextIndicatorProgress = {
    if (indicatorProgress < 1f) {
      indicatorProgress += 0.2f
      changeScreen(currentScreen + 1)
    }
  }

  val prevIndicatorProgress = {
    if (indicatorProgress > 0.2f) {
      indicatorProgress -= 0.2f
      changeScreen(currentScreen - 1)
    }
  }

  val titleBasedOnScreen = when (currentScreen) {
    0 -> "Profil Lembaga"
    1 -> "Program Lembaga"
    2 -> "Pendaftaran Peserta"
    3 -> "Pertanyaan Umum"
    4 -> "Ringkasan Data Pendaftaran"
    else -> "Wrong Screen"
  }

  Column(
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(40.dp),
  ) {
    TopSection(
      indicatorProgress = indicatorProgress,
      titleBasedOnScreen = titleBasedOnScreen
    )
    BottomSection(
      nextIndicatorProgress = nextIndicatorProgress,
      prevIndicatorProgress = prevIndicatorProgress,
      initialState = initialState,
      currentScreen = currentScreen,
      onInitialScreenChange = { initialState = it }
    )
  }
}

@Composable
private fun TopSection(
  indicatorProgress : Float,
  titleBasedOnScreen : String
) {
  val animatedProgress by animateFloatAsState(
    targetValue = indicatorProgress,
    animationSpec = tween(durationMillis = 500),
    label = "Animated Progress"
  )

  Column(
    modifier = Modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "Pendaftaran LEAD Indonesia 2023",
      style = StyledText.MobileLargeMedium
    )
    AnimatedContent(
      targetState = titleBasedOnScreen,
      transitionSpec = {
        slideInVertically { it } + fadeIn() togetherWith
          slideOutVertically { - it } + fadeOut()
      }, label = "Title Registrasi Animation Content"
    ) { title ->
      Text(
        text = title,
        style = StyledText.MobileLargeBold
      )
    }
    LinearProgressIndicator(
      progress = { animatedProgress },
      modifier = Modifier.fillMaxWidth(),
      color = ColorPalette.PrimaryColor700,
    )
  }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomSection(
  nextIndicatorProgress : () -> Unit,
  prevIndicatorProgress : () -> Unit,
  initialState : Int,
  onInitialScreenChange : (Int) -> Unit,
  currentScreen : Int
) {
  val verticalScroll = rememberScrollState()

  var expandedProvinsi by remember { mutableStateOf(false) }
  var expandedKota by remember { mutableStateOf(false) }
  var expandedDate by remember { mutableStateOf(false) }
  var expandedLembagaSosial by remember { mutableStateOf(false) }
  var expandedFokusIsu by remember { mutableStateOf(false) }

  val datePickerState = rememberDatePickerState()
  val selectedDate = datePickerState.selectedDateMillis?.let { convertMillisToDate(it) } ?: ""

  Box(
    modifier = Modifier
      .fillMaxSize()
      .pointerInput(expandedDate, expandedProvinsi) {
        detectTapGestures {
          expandedDate = false
          expandedProvinsi = false
        }
      }
  ) {


    AnimatedContentSlide(
      currentScreen = currentScreen,
      initialState = initialState,
      label = "Registrasi Animation Content",
    ) { targetScreen ->
      Column(
        modifier = Modifier
          .verticalScroll(verticalScroll),
        verticalArrangement = Arrangement.spacedBy(24.dp)

      ) {
        when (targetScreen) {
          0 -> FirstScreen(
            selectedDate = selectedDate,
            datePickerState = datePickerState,
            expandedDate = expandedDate,
            onExpandedDateChange = { expandedDate = it },
            expandedProvinsi = expandedProvinsi,
            onExpandedProvinsiChange = { expandedProvinsi = it },
            expandedKota = expandedKota,
            onExpandedKotaChange = { expandedKota = it },
            expandedLembagaSosial = expandedLembagaSosial,
            onExpandedLembagaSosialChange = { expandedLembagaSosial = it },
            expandedFokusIsu = expandedFokusIsu,
            onExpandedFokusIsuChange = { expandedFokusIsu = it },
            nextIndicatorProgress = nextIndicatorProgress
          )

          1 -> SecondScreen(
            prevIndicatorProgress = prevIndicatorProgress,
            nextIndicatorProgress = nextIndicatorProgress
          )
        }

        LaunchedEffect(currentScreen) { onInitialScreenChange(currentScreen) }
      }
    }
  }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FirstScreen(
  selectedDate : String,
  datePickerState : DatePickerState,
  expandedDate : Boolean,
  onExpandedDateChange : (Boolean) -> Unit,
  expandedProvinsi : Boolean,
  onExpandedProvinsiChange : (Boolean) -> Unit,
  expandedKota : Boolean,
  onExpandedKotaChange : (Boolean) -> Unit,
  expandedLembagaSosial : Boolean,
  onExpandedLembagaSosialChange : (Boolean) -> Unit,
  expandedFokusIsu : Boolean,
  onExpandedFokusIsuChange : (Boolean) -> Unit,


  nextIndicatorProgress : () -> Unit,


  ) {

  CustomOutlinedTextField(
    label = "Nama Lembaga",
    value = "",
    onValueChange = {},
    placeholder = "Masukkan nama lembaga",
    modifier = Modifier.fillMaxWidth(),
    labelDefaultColor = ColorPalette.Monochrome400,
    rounded = 40
  )
  CustomOutlinedTextFieldDropdownDate(
    label = "Tanggal Berdiri",
    value = selectedDate,
    placeholder = "DD/MM/YYYY",
    modifier = Modifier.fillMaxWidth(),
    labelDefaultColor = ColorPalette.Monochrome400,
    datePickerState = datePickerState,
    expanded = expandedDate,
    onChangeExpanded = {
      onExpandedDateChange(it)
    }
  )
  CustomOutlinedTextField(
    label = "Email Formal Lembaga",
    value = "",
    onValueChange = {},
    placeholder = "contoh@bcf.or.id",
    modifier = Modifier.fillMaxWidth(),
    labelDefaultColor = ColorPalette.Monochrome400,
    rounded = 40
  )
  CustomOutlinedTextField(
    label = "Masukkan alamat lengkap lembaga",
    value = "",
    onValueChange = {},
    placeholder = "contoh@bcf.or.id",
    modifier = Modifier.fillMaxWidth(),
    labelDefaultColor = ColorPalette.Monochrome400,
    rounded = 40

  )
  CustomOutlinedTextFieldDropdown(
    label = "Provinsi",
    value = "",
    onValueChange = {},
    placeholder = "Pilih Provinsi",
    modifier = Modifier.fillMaxWidth(),
    labelDefaultColor = ColorPalette.Monochrome400,
    dropdownItems = provinsis,
    expanded = expandedProvinsi,
    onChangeExpanded = {
      onExpandedProvinsiChange(it)
    }
  )
  CustomOutlinedTextFieldDropdown(
    label = "Kota / Kabupaten",
    value = "",
    onValueChange = {},
    placeholder = "Pilih kota/kabupaten",
    modifier = Modifier.fillMaxWidth(),
    labelDefaultColor = ColorPalette.Monochrome400,
    dropdownItems = provinsis,
    expanded = expandedKota,
    onChangeExpanded = {
      onExpandedKotaChange(it)
    }
  )
  CustomOutlinedTextFieldDropdown(
    label = "Jenis Lembaga Sosial",
    value = "",
    onValueChange = {},
    placeholder = "Pilih jenis cluster lembaga sosial",
    modifier = Modifier.fillMaxWidth(),
    labelDefaultColor = ColorPalette.Monochrome400,
    expanded = expandedLembagaSosial,
    onChangeExpanded = {
      onExpandedLembagaSosialChange(it)
    },
    dropdownItems = provinsis
  )
  CustomOutlinedTextFieldDropdown(
    label = "Fokus Isu",
    value = "",
    onValueChange = {},
    placeholder = "Pilih fokus isu",
    modifier = Modifier.fillMaxWidth(),
    labelDefaultColor = ColorPalette.Monochrome400,
    dropdownItems = provinsis,
    expanded = expandedFokusIsu,
    onChangeExpanded = {
      onExpandedFokusIsuChange(it)
    }
  )
  CustomOutlinedTextField(
    label = "Profil Singkat Lembaga",
    value = "",
    onValueChange = {},
    placeholder = "Masukkan profil singkat lembaga",
    modifier = Modifier.fillMaxWidth(),
    labelDefaultColor = ColorPalette.Monochrome400,
    rounded = 40
  )
  CustomOutlinedTextField(
    label = "Apa alasan anda mengikuti LEAD Indonesia 2023?",
    value = "",
    onValueChange = {},
    placeholder = "Masukkan alasan anda mengikuti LEAD Indonesia 2023",
    modifier = Modifier.fillMaxWidth(),
    labelDefaultColor = ColorPalette.Monochrome400,
    rounded = 40
  )
  Box(
    modifier = Modifier
      .fillMaxWidth(),
    contentAlignment = Alignment.CenterEnd
  ) {
    PrimaryButton(
      text = "Lanjut",
      onClick = {
        nextIndicatorProgress()
      }
    )

  }

}


@Composable
private fun SecondScreen(
  prevIndicatorProgress : () -> Unit,
  nextIndicatorProgress : () -> Unit
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(24.dp)
  ) {
    Text(
      text = "Feedback Peserta",
      style = StyledText.MobileLargeBold
    )
    Row(
      modifier = Modifier
        .fillMaxWidth(),
      horizontalArrangement = Arrangement.End,
      verticalAlignment = Alignment.CenterVertically
    ) {
      PrimaryButton(
        text = "Kembali",
        onClick = {
          prevIndicatorProgress()
        }
      )
      PrimaryButton(
        text = "Lanjut",
        onClick = {
          nextIndicatorProgress()
        }
      )

    }

  }
}

val provinsis = listOf(
  "Aceh",
  "Sumatera Utara",
  "Sumatera Barat",
  "Riau",
  "Jambi",
  "Sumatera Selatan",
  "Bengkulu",
  "Lampung",
  "Kepulau"
)
