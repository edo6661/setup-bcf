package com.example.slicingbcf.implementation.auth.registrasi

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.slicingbcf.constant.ColorPalette
import com.example.slicingbcf.constant.StyledText
import com.example.slicingbcf.data.local.WilayahJangkauan
import com.example.slicingbcf.implementation.auth.registrasi.ConstantRegistrasi.Companion.provinsis
import com.example.slicingbcf.ui.animations.AnimatedContentSlide
import com.example.slicingbcf.ui.shared.PrimaryButton
import com.example.slicingbcf.ui.shared.message.SecondaryButton
import com.example.slicingbcf.ui.shared.textfield.CustomOutlinedTextField
import com.example.slicingbcf.ui.shared.textfield.CustomOutlinedTextFieldDropdown
import com.example.slicingbcf.ui.shared.textfield.CustomOutlinedTextFieldDropdownDate
import com.example.slicingbcf.ui.upload.FileUploadSection
import com.example.slicingbcf.util.convertMillisToDate


// TODO: build second screen - latest screen

@Composable
fun RegistrasiScreen(
  modifier : Modifier,
  viewModel : RegistrasiViewModel = hiltViewModel()
) {

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
  var expandedPilihKota by remember { mutableStateOf(false) }
  var expandedWilayahJangkauanProgram by remember { mutableStateOf(false) }
  var expandedJangkauanProgram by remember { mutableStateOf(false) }
  var expandedJenisKelamin by remember { mutableStateOf(false) }
  var expandedPendidikan by remember { mutableStateOf(false) }
  var selectedFileUriProfilPerusahaan by remember { mutableStateOf<Uri?>(null) }
  val filePickerLauncherProfilPerusahaan = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.OpenDocument(),
    onResult = { uri -> selectedFileUriProfilPerusahaan = uri }
  )
  val deleteFileProfilPerusahaan = { selectedFileUriProfilPerusahaan = null }
  var selectedFileUriProposalMitra by remember { mutableStateOf<Uri?>(null) }
  val filePickerLauncherProposalMitra = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.OpenDocument(),
    onResult = { uri -> selectedFileUriProposalMitra = uri }
  )
  val deleteFileProposalMitra = { selectedFileUriProposalMitra = null }

  var selectedFileUriKTP by remember { mutableStateOf<Uri?>(null) }
  val filePickerLauncherKTP = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.OpenDocument(),
    onResult = { uri -> selectedFileUriKTP = uri }
  )
  val deleteFileKTP = { selectedFileUriKTP = null }
  var selectedFileUriCV by remember { mutableStateOf<Uri?>(null) }
  val filePickerLauncherCV = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.OpenDocument(),
    onResult = { uri -> selectedFileUriCV = uri }
  )
  val deleteFileCV = { selectedFileUriCV = null }
  var selectedFileUriLaporanAkhirTahun by remember { mutableStateOf<Uri?>(null) }
  val filePickerLauncherLaporanAkhirTahun = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.OpenDocument(),
    onResult = { uri -> selectedFileUriLaporanAkhirTahun = uri }
  )
  val deleteFileLaporanAkhirTahun = { selectedFileUriLaporanAkhirTahun = null }


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
            nextIndicatorProgress = nextIndicatorProgress,
            filePickerLauncher = filePickerLauncherProfilPerusahaan,
            selectedFileUri = selectedFileUriProfilPerusahaan,
            deleteFile = deleteFileProfilPerusahaan
          )

          1 -> SecondScreen(
            prevIndicatorProgress = prevIndicatorProgress,
            nextIndicatorProgress = nextIndicatorProgress,
            expandedPilihKota = expandedPilihKota,
            expandedJangkauanProgram = expandedJangkauanProgram,
            expandedWilayahJangkauanProgram = expandedJangkauanProgram,
            onExpandedJangkauanProgramChange = {
              expandedJangkauanProgram = it
            },
            onExpandedPilihKotaChange = {
              expandedPilihKota = it
            },
            onExpandedWilayahJangkauanProgramChange = {
              expandedWilayahJangkauanProgram = it
            },
            selectedFileUri = selectedFileUriProposalMitra,
            filePickerLauncher = filePickerLauncherProposalMitra,
            deleteFile = deleteFileProposalMitra
          )

          2 -> ThirdScreen(
            prevIndicatorProgress = prevIndicatorProgress,
            nextIndicatorProgress = nextIndicatorProgress,
            expandedPendidikan = expandedPendidikan,
            expandedJenisKelamin = expandedJenisKelamin,
            onExpandedPendidikanChange = {
              expandedPendidikan = it
            },
            onExpandedJenisKelaminChange = {
              expandedJenisKelamin = it
            },
            filePickerLauncherKTP = filePickerLauncherKTP,
            selectedFileUriKTP = selectedFileUriKTP,
            deleteFileKTP = deleteFileKTP,
            filePickerLauncherCV = filePickerLauncherCV,
            selectedFileUriCV = selectedFileUriCV,
            deleteFileCV = deleteFileCV


          )

          3 -> FourthScreen(
            prevIndicatorProgress = prevIndicatorProgress,
            nextIndicatorProgress = nextIndicatorProgress,
            selectedFileUri = selectedFileUriLaporanAkhirTahun,
            filePickerLauncher = filePickerLauncherLaporanAkhirTahun,
            deleteFile = deleteFileLaporanAkhirTahun
          )

          4 -> FifthScreen(
            prevIndicatorProgress = prevIndicatorProgress,

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
  filePickerLauncher : ManagedActivityResultLauncher<Array<String>, Uri?>,
  selectedFileUri : Uri?,
  deleteFile : () -> Unit
) {

  var isFocusedNamaLembaga by remember { mutableStateOf(false) }
  var namaLembaga by remember { mutableStateOf("") }

  CustomOutlinedTextField(
    label = "Nama Lembaga",
    value = namaLembaga,
    onValueChange = { namaLembaga = it },
    placeholder = "Masukkan nama lembaga",
    modifier = Modifier.fillMaxWidth(),
    isFocused = isFocusedNamaLembaga,
    onFocusChange = { isFocusedNamaLembaga = it },
    labelDefaultColor = ColorPalette.Monochrome400,
    labelFocusedColor = ColorPalette.PrimaryColor700,
    borderFocusedColor = ColorPalette.PrimaryColor700,
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
  FileUploadSection(
    title = "Dokumentasi sesi mentoring cluster",
    buttonText = "Klik untuk unggah file dokumentasi",
    onFileSelect = { filePickerLauncher.launch(arrayOf("image/*", "application/pdf")) },
    selectedFileUri = selectedFileUri,
    deleteFile = deleteFile
  )
  NextPrevButton(
    nextIndicatorProgress = nextIndicatorProgress,
    isPrevExist = false
  )

}

@Composable
private fun SecondScreen(
  prevIndicatorProgress : () -> Unit,
  nextIndicatorProgress : () -> Unit,
  expandedJangkauanProgram : Boolean,
  onExpandedJangkauanProgramChange : (Boolean) -> Unit,
  expandedWilayahJangkauanProgram : Boolean,
  onExpandedWilayahJangkauanProgramChange : (Boolean) -> Unit,
  expandedPilihKota : Boolean,
  onExpandedPilihKotaChange : (Boolean) -> Unit,
  selectedFileUri : Uri?,
  filePickerLauncher : ManagedActivityResultLauncher<Array<String>, Uri?>,
  deleteFile : () -> Unit
) {
  var fields by remember { mutableStateOf(listOf(Pair("", ""))) }

  Column(
    verticalArrangement = Arrangement.spacedBy(16.dp),
    modifier = Modifier.animateContentSize()
  ) {
    CustomOutlinedTextFieldDropdown(
      label = "Jangkauan Program",
      value = "",
      onValueChange = {},
      placeholder = "Pilih Jangkauan Program",
      modifier = Modifier.fillMaxWidth(),
      labelDefaultColor = ColorPalette.Monochrome400,
      dropdownItems = provinsis,
      expanded = expandedJangkauanProgram,
      onChangeExpanded = {
        onExpandedJangkauanProgramChange(it)
      }
    )
    CustomOutlinedTextFieldDropdown(
      label = "Wilayah Jangkauan Program",
      value = "",
      onValueChange = {},
      placeholder = "Pilih Wilayah Jangkauan Program",
      modifier = Modifier.fillMaxWidth(),
      labelDefaultColor = ColorPalette.Monochrome400,
      dropdownItems = provinsis,
      expanded = expandedWilayahJangkauanProgram,
      onChangeExpanded = {
        onExpandedWilayahJangkauanProgramChange(it)
      }
    )

    Column(
      verticalArrangement = Arrangement.spacedBy(12.dp),
      modifier = Modifier.animateContentSize()
    ) {
      Text(
        text = "Jumlah Angka Penerimaan Manfaat*",
        style = StyledText.MobileSmallMedium,
        color = ColorPalette.PrimaryColor700
      )

      fields.forEachIndexed { index, field ->
        CustomRowWithFields(
          dropdownLabel = "DKI Jakarta",
          dropdownValue = field.first,
          onDropdownValueChange = { newValue ->
            fields = fields.toMutableList().apply {
              this[index] = this[index].copy(first = newValue)
            }
          },
          dropdownPlaceholder = "Pilih kota/kabupaten",
          dropdownItems = provinsis,
          expanded = expandedPilihKota,
          onExpandedChange = {
            onExpandedPilihKotaChange(it)
          },
          jumlahValue = field.second,
          onJumlahValueChange = { newValue ->
            fields = fields.toMutableList().apply {
              this[index] = this[index].copy(second = newValue)
            }
          }
        )
      }

      Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
      ) {
        SecondaryButton(
          onClick = {
            fields = fields + Pair("", "")
          },
          text = "Tambah"
        )
        AnimatedVisibility(
          visible = fields.size > 1,
          enter = fadeIn(),
          exit = fadeOut()
        ) {
          SecondaryButton(
            onClick = {
              fields = fields.dropLast(1)
            },
            text = "Hapus"
          )
        }

      }
    }

    CustomOutlinedTextField(
      label = "Target Utama Program",
      value = "",
      onValueChange = {},
      placeholder = "Ketik target utama program",
      modifier = Modifier.fillMaxWidth(),
      labelDefaultColor = ColorPalette.Monochrome400,
      rounded = 40
    )

    FileUploadSection(
      title = "Unggah Proposal Program Dengan Mitra",
      buttonText = "Klik untuk unggah file",
      onFileSelect = { filePickerLauncher.launch(arrayOf("image/*", "application/pdf")) },
      selectedFileUri = selectedFileUri,
      deleteFile = deleteFile
    )


    NextPrevButton(
      nextIndicatorProgress = nextIndicatorProgress,
      prevIndicatorProgress = prevIndicatorProgress
    )
  }
}

@Composable
private fun ThirdScreen(
  prevIndicatorProgress : () -> Unit,
  nextIndicatorProgress : () -> Unit,
  expandedPendidikan : Boolean,
  onExpandedPendidikanChange : (Boolean) -> Unit,
  expandedJenisKelamin : Boolean,
  onExpandedJenisKelaminChange : (Boolean) -> Unit,
  filePickerLauncherKTP : ManagedActivityResultLauncher<Array<String>, Uri?>,
  selectedFileUriKTP : Uri?,
  deleteFileKTP : () -> Unit,
  filePickerLauncherCV : ManagedActivityResultLauncher<Array<String>, Uri?>,
  selectedFileUriCV : Uri?,
  deleteFileCV : () -> Unit
) {
  CustomOutlinedTextField(
    label = "Nama Lengkap Peserta",
    value = "",
    onValueChange = {},
    placeholder = "Masukkan nama lengkap",
    modifier = Modifier.fillMaxWidth(),
    labelDefaultColor = ColorPalette.Monochrome400,
    rounded = 40
  )
  CustomOutlinedTextField(
    label = "Posisi",
    value = "",
    onValueChange = {},
    placeholder = "Masukkan posisi",
    modifier = Modifier.fillMaxWidth(),
    labelDefaultColor = ColorPalette.Monochrome400,
    rounded = 40
  )

  CustomOutlinedTextFieldDropdown(
    label = "Pendidikan Terakhir",
    value = "",
    onValueChange = {},
    placeholder = "Pilih Pendidikan Terakhir",
    modifier = Modifier.fillMaxWidth(),
    labelDefaultColor = ColorPalette.Monochrome400,
    dropdownItems = provinsis,
    expanded = expandedPendidikan,
    onChangeExpanded = {
      onExpandedPendidikanChange(it)
    }
  )
  CustomOutlinedTextField(
    label = "Jurusan Pendidikan Terakhir",
    value = "",
    onValueChange = {},
    placeholder = "Ketik jurusan pendidikan terakhir anda",
    modifier = Modifier.fillMaxWidth(),
    labelDefaultColor = ColorPalette.Monochrome400,
    rounded = 40
  )

  CustomOutlinedTextFieldDropdown(
    label = "Jenis Kelamin",
    value = "",
    onValueChange = {},
    placeholder = "Pilih jenis kelamin anda",
    modifier = Modifier.fillMaxWidth(),
    labelDefaultColor = ColorPalette.Monochrome400,
    dropdownItems = provinsis,
    expanded = expandedJenisKelamin,
    onChangeExpanded = {
      onExpandedJenisKelaminChange(it)
    }
  )
  CustomOutlinedTextField(
    label = "Nomor Whatsapp Peserta",
    value = "",
    onValueChange = {},
    placeholder = "Contoh: 08980861214",
    modifier = Modifier.fillMaxWidth(),
    labelDefaultColor = ColorPalette.Monochrome400,
    rounded = 40
  )

  CustomOutlinedTextField(
    label = "Email Peserta",
    value = "",
    onValueChange = {},
    placeholder = "Contoh: @gmail.com",
    modifier = Modifier.fillMaxWidth(),
    labelDefaultColor = ColorPalette.Monochrome400,
    rounded = 40
  )
  FileUploadSection(
    title = "Unggah Kartu Tanda Penduduk (KTP)",
    buttonText = "Klik untuk unggah file",
    onFileSelect = { filePickerLauncherKTP.launch(arrayOf("image/*", "application/pdf")) },
    selectedFileUri = selectedFileUriKTP,
    deleteFile = deleteFileKTP
  )
  FileUploadSection(
    title = "Unggah Curriculum Vitae (CV)",
    buttonText = "Klik untuk unggah file",
    onFileSelect = { filePickerLauncherCV.launch(arrayOf("image/*", "application/pdf")) },
    selectedFileUri = selectedFileUriCV,
    deleteFile = deleteFileCV
  )

  Column(
    verticalArrangement = Arrangement.spacedBy(20.dp)
  ) {
    Column(
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Text(
        text = "Apakah ada pengurus lain yang akan diikutsertakan sebagai peserta?*",
        style = StyledText.MobileSmallMedium,
        color = ColorPalette.PrimaryColor700
      )
      Text(
        text = "Mohon isi apabila pada kegiatan Mini Training, Initial Mentoring, dan Pendampingan Intensif memerlukan rekan pengganti pada hari tertentu.",
        style = StyledText.MobileXsRegular,
        color = ColorPalette.Monochrome300
      )
    }
    Column(
      verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
      RowRadioButton(
        selected = true,
        onClick = {},
        text = "Ya, terdapat rekan saya sebagai peserta 2"
      )
      RowRadioButton(
        selected = false,
        onClick = {},
        text = "Tidak, hanya saya sendiri sebagai penanggung jawab utama"
      )
    }
  }

  Column(
    verticalArrangement = Arrangement.spacedBy(20.dp)
  ) {

    Text(
      text = "Apakah ada pengurus lain yang akan diikutsertakan sebagai peserta?*",
      style = StyledText.MobileSmallMedium,
      color = ColorPalette.PrimaryColor700
    )

    Column(
      verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
      RowCheckboxButton(
        checked = true,
        onCheckedChange = {},
        text = "Saya bersedia mengikuti agenda Mini Training LEAD Indonesia yang akan dilakukan secara online selama 5 hari"
      )
      RowCheckboxButton(
        checked = false,
        onCheckedChange = {},
        text = "Saya bersedia mengikuti agenda Initial Mentoring LEAD Indonesia yang akan dilakukan secara online selama 5 hari"
      )
      RowCheckboxButton(
        checked = false,
        onCheckedChange = {},
        text = "Saya bersedia mengikuti agenda Pendampingan Intensif bersama pada Mentor LEAD Indonesia yang akan dilakukan secara online minimal 2-3 kali setiap bulan"
      )
    }
  }
  Column(
    verticalArrangement = Arrangement.spacedBy(20.dp)
  ) {
    Text(
      text = "Apakah ada pengurus lain yang akan diikutsertakan sebagai peserta?*",
      style = StyledText.MobileSmallMedium,
      color = ColorPalette.PrimaryColor700
    )
    CustomOutlinedTextField(
      label = "Masukkan alasan anda tidak bersedia mengikuti agenda tersebut",
      value = "",
      onValueChange = {},
      placeholder = "Ketik alasan anda tidak bersedia mengikuti agenda tersebut",
      labelDefaultColor = ColorPalette.Monochrome400,
      modifier = Modifier.fillMaxWidth(),
    )

  }

  NextPrevButton(
    nextIndicatorProgress = nextIndicatorProgress,
    prevIndicatorProgress = prevIndicatorProgress,
  )

}

@Composable
fun RowRadioButton(
  selected : Boolean,
  onClick : () -> Unit,
  text : String,

  ) {
  Row(
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .fillMaxWidth()
      .border(
        width = 1.dp,
        color = ColorPalette.Monochrome150,
        shape = RoundedCornerShape(8.dp)
      )
      .padding(
        8.dp
      )
  ) {
    RadioButton(
      selected = selected,
      onClick = onClick,
      colors = RadioButtonDefaults.colors(
        selectedColor = ColorPalette.PrimaryColor700,
        unselectedColor = ColorPalette.Monochrome400
      )
    )
    Text(
      text = text,
      style = StyledText.MobileSmallRegular,
      color = ColorPalette.OnSurface
    )
  }
}

@Composable
fun RowCheckboxButton(
  checked : Boolean,
  onCheckedChange : () -> Unit,
  text : String,

  ) {
  Row(
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .fillMaxWidth()

      .padding(
        8.dp
      )
  ) {
    Checkbox(
      checked = checked,
      onCheckedChange = { onCheckedChange() },
      colors = CheckboxDefaults.colors(
        checkedColor = ColorPalette.PrimaryColor700,
        uncheckedColor = ColorPalette.Monochrome400
      )
    )
    Text(
      text = text,
      style = StyledText.MobileSmallRegular,
      color = ColorPalette.OnSurface
    )
  }
}

@Composable
fun FourthScreen(
  prevIndicatorProgress : () -> Unit,
  nextIndicatorProgress : () -> Unit,
  filePickerLauncher : ManagedActivityResultLauncher<Array<String>, Uri?>,
  selectedFileUri : Uri?,
  deleteFile : () -> Unit
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {

    Text(
      text = "Apakah anda pernah mengikuti pelatihan atau memiliki pengetahuan terkait desain program sebelum mendaftar LEAD Indonesia 2024?*",
      style = StyledText.MobileSmallMedium,
      color = ColorPalette.PrimaryColor700
    )

    CustomOutlinedTextFieldDropdown(
      label = "Pilih Jawaban",
      value = "",
      onValueChange = {},
      placeholder = "Pilih Jawaban",
      modifier = Modifier.fillMaxWidth(),
      labelDefaultColor = ColorPalette.Monochrome400,
      dropdownItems = provinsis,
      expanded = false,
      onChangeExpanded = {}
    )
  }
  Column(
    verticalArrangement = Arrangement.spacedBy(20.dp)
  ) {
    Text(
      text = "Darimana anda mengetahui LEAD Indonesia?*",
      style = StyledText.MobileSmallMedium,
      color = ColorPalette.PrimaryColor700
    )
    Column {
      RowCheckboxButton(
        checked = true,
        onCheckedChange = {},
        text = "Instagram @bakriecenter"
      )
      RowCheckboxButton(
        checked = false,
        onCheckedChange = {},
        text = "Youtube @bakriecenter"
      )
      RowCheckboxButton(
        checked = false,
        onCheckedChange = {},
        text = "Website Bakrie Center Foundation (http//bcr.or.id)"
      )
      RowCheckboxButton(
        checked = false,
        onCheckedChange = {},
        text = "Teman atau rekan kerja"
      )
      RowCheckboxButton(
        checked = false,
        onCheckedChange = {},
        text = "Alumni LEAD Indonesia"
      )
      RowCheckboxButton(
        checked = false,
        onCheckedChange = {},
        text = "Yang lain: _____________"
      )
    }

  }
  Column(
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {

    Text(
      text = "Apa yang anda ketahui terkait desain program?",
      style = StyledText.MobileSmallMedium,
      color = ColorPalette.PrimaryColor700
    )

    CustomOutlinedTextField(
      label = "Jelaskan pengetahuan mu terkait LEAD Indonesia",
      value = "",
      onValueChange = {},
      placeholder = "Ketik pengetahuanmu terkait LEAD Indonesia",
      modifier = Modifier.fillMaxWidth(),
    )
  }
  ColumnTextField(
    label = "Apakah yang anda ketahui terkait sustainability atau keberlanjutan?",
    value = "",
    text = "Jelaskan pengetahuanmu terkait sustainability atau keberlanjutan",
    onValueChange = {},
    placeholder = "Ketik pengetahuanmu terkait sustainability atau keberlanjutan",
    modifier = Modifier.fillMaxWidth()
  )
  ColumnTextField(
    label = "Apakah yang anda ketahui terkait social report atau laporan social?",
    value = "",
    text = "Jelaskan pengetahuanmu terkait social report atau laporan social",
    onValueChange = {},
    placeholder = "Ketik pengetahuanmu terkait social report atau laporan social",
    modifier = Modifier.fillMaxWidth()
  )
  FileUploadSection(
    title = "Unggah laporan akhir tahun atau laporan pertanggungjawaban pelaksanaan program instansi",
    buttonText = "Klik untuk unggah file",
    onFileSelect = { filePickerLauncher.launch(arrayOf("image/*", "application/pdf")) },
    selectedFileUri = selectedFileUri,
    deleteFile = deleteFile
  )
  ColumnTextField(
    label = "Jelaskan ekspetasi anda setelah mengikuti kegiatan LEAD Indonesia 2023?",
    value = "",
    text = "Jelaskan ekspetasi anda setelah mengikuti kegiatan LEAD Indonesia 2023",
    onValueChange = {},
    placeholder = "Ketik ekspetasi anda setelah mengikuti kegiatan LEAD Indonesia 2023",
    modifier = Modifier.fillMaxWidth()
  )
  ColumnTextField(
    label = "Apakah ada hal lain yang ingin anda tanyakan atau sampaikan terkait LEAD Indonesia 2023?",
    value = "",
    text = "Jelaskan hal lain yang ingin anda tanyakan atau sampaikan terkait LEAD Indonesia 2023",
    onValueChange = {},
    placeholder = "Ketik hal lain yang ingin anda tanyakan atau sampaikan terkait LEAD Indonesia 2023",
    modifier = Modifier.fillMaxWidth()
  )


  NextPrevButton(
    nextIndicatorProgress = nextIndicatorProgress,
    prevIndicatorProgress = prevIndicatorProgress
  )


}

@Composable
fun FifthScreen(

  prevIndicatorProgress : () -> Unit,
) {
  val headerTable = listOf("No", "Provinsi", "Jumlah Penerima Manfaaat", "Rincian")
  val columnWeights = listOf(
    0.5f,
    1f,
    1f,
    1f
  )
  val rowsJangkauanProgram = listOf(
    WilayahJangkauan(
      provinsi = "DKI Jakarta",
      jumlahPenerimaManfaat = 100,
    ),
    WilayahJangkauan(
      provinsi = "Jawa Barat",
      jumlahPenerimaManfaat = 200,
    ),
  ).mapIndexed { index, wilayahJangkauan ->
    listOf(
      (index + 1).toString(),
      wilayahJangkauan.provinsi,
      wilayahJangkauan.jumlahPenerimaManfaat.toString(),
      "Rincian"
    )
  }
  Column(
    verticalArrangement = Arrangement.spacedBy(20.dp)
  ) {
    Title(
      text = "Profil Lembaga"
    )
    ColumnKeyValue(
      key = "Nama Lembaga",
      value = "Bakrie Center Foundation"
    )
    ColumnKeyValue(
      key = "Email Formal Lembaga",
      value = "bcf@gmail.com",
    )
    ColumnKeyValue(
      key = "Alamat Lengkap Lembaga",
      value = "Jl. Jend. Sudirman No. 1, Jakarta Pusat",
    )
    ColumnKeyValue(
      key = "Jenis Lembaga",
      value = "Gerakan",
    )
    ColumnKeyValue(
      key = "Fokus Isu",
      value = "Pendidikan",
    )
    ColumnKeyValue(
      key = "Alasan Mengikuti Program",
      value = "Karena ingin mengembangkan komunitas"
    )
    ColumnKeyValue(
      key = "Cakupan/Jangkauan Program",
      value = "Provinsi"
    )
    ColumnKeyValue(
      key = "Target Utama Program",
      value = "Kepala Keluarga, Anak Jalanan, Pasien TBC"
    )
    Text(
      text = "Wilayah Jangkauan Program",
      style = StyledText.MobileSmallSemibold,
      color = ColorPalette.Monochrome800
    )
    Table(
      headers = headerTable,
      columnWeights = columnWeights,
      rows = rowsJangkauanProgram
    )
    HorizontalDivider(
      modifier = Modifier.fillMaxWidth()
    )
    Title(
      text = "Profil Peserta"
    )
    ColumnKeyValue(
      key = "Nama Lengkap ",
      value = "Budi Santoso"
    )
    ColumnKeyValue(
      key = "Posisi",
      value = "Ketua Instansi"
    )
    ColumnKeyValue(
      key = "Pendidiakn Terakhir",
      value = "S1"
    )
    ColumnKeyValue(
      key = "Pendidikan Terakhir",
      value = "S1 - Teknik Informatika"
    )
    ColumnKeyValue(
      key = "Nomor Whatsapp",
      value = "08980861214"
    )
    ColumnKeyValue(
      key = "Email",
      value = "aurel@gmail.com"
    )
    SecondaryButton(
      text = "Ubah Profil Peserta",
      onClick = {},
    )
    HorizontalDivider(
      modifier = Modifier.fillMaxWidth()
    )
    Column {
      RowCheckboxButton(
        text = "Saya sudah membaca dan memahami https://bit.ly/LEADBCF-2023",
        checked = true,
        onCheckedChange = {}
      )
      RowCheckboxButton(
        text = "Saya mengkonfirmasi bahwa seluruh informasi yang saya berikan di atas adalah akurat dan terkini",
        checked = false,
        onCheckedChange = {}
      )
    }
  }

  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
    verticalAlignment = Alignment.CenterVertically
  ) {
    SecondaryButton(
      text = "Sebelumnya",
      onClick = { prevIndicatorProgress() }
    )
    PrimaryButton(
      text = "Kirim",
      onClick = { }
    )
  }
}

@Composable
fun Table(
  headers : List<String>,
  columnWeights : List<Float>,
  rows : List<List<String>>
) {
  Column(
  ) {
    TableRow {
      headers.forEachIndexed { index, header ->
        TableCell(
          isHeader = true,
          text = header,
          modifier = Modifier.weight(columnWeights.getOrElse(index) { 1f })
        )
      }
    }

    rows.forEach { row ->
      TableRow {
        row.forEachIndexed { index, cell ->
          TableCell(
            text = cell,
            modifier = Modifier.weight(columnWeights.getOrElse(index) { 1f })
          )
        }
      }
    }
  }
}

@Composable
fun TableRow(
  content : @Composable () -> Unit
) {
  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier
      .drawBehind {
        drawLine(
          color = ColorPalette.OutlineVariant,
          strokeWidth = 1.dp.toPx(),
          start = Offset(0f, 0f),
          end = Offset(size.width, 0f)
        )
        drawLine(
          color = ColorPalette.OutlineVariant,
          strokeWidth = 1.dp.toPx(),
          start = Offset(0f, size.height),
          end = Offset(size.width, size.height)
        )
      }
      .padding(horizontal = 16.dp, vertical = 8.dp)
      .fillMaxWidth()
  ) {
    content()
  }
}

@Composable
fun TableCell(
  modifier : Modifier = Modifier,
  isHeader : Boolean = false,
  text : String,
) {
  val fontStyle = if (isHeader) StyledText.MobileSmallSemibold else StyledText.MobileSmallRegular

  Text(
    text = text,
    style = fontStyle,
    color = ColorPalette.Black,
    modifier = modifier
      .padding(vertical = 8.dp)
      .fillMaxWidth(),
    textAlign = TextAlign.Start
  )
}


@Composable
private fun Title(
  text : String
) {
  Text(
    text = text,
    style = StyledText.MobileSmallSemibold,
    color = ColorPalette.PrimaryColor700
  )
}

@Composable
private fun ColumnKeyValue(
  key : String,
  value : String
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    Text(
      text = key,
      style = StyledText.MobileSmallSemibold,
      color = ColorPalette.Monochrome800
    )
    Text(
      text = value,
      style = StyledText.MobileSmallRegular,
      color = ColorPalette.Monochrome800
    )

  }
}


@Composable
private fun NextPrevButton(
  nextIndicatorProgress : () -> Unit,
  prevIndicatorProgress : () -> Unit = {},
  isPrevExist : Boolean = true,
  isNextExist : Boolean = true
) {

  Column(
    verticalArrangement = Arrangement.spacedBy(24.dp)
  ) {
    HorizontalDivider(modifier = Modifier.fillMaxWidth())

    when {
      isPrevExist && isNextExist -> {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
          verticalAlignment = Alignment.CenterVertically
        ) {
          SecondaryButton(
            text = "Sebelumnya",
            onClick = { prevIndicatorProgress() }
          )
          PrimaryButton(
            text = "Berikutnya",
            onClick = { nextIndicatorProgress() }
          )
        }
      }

      isPrevExist                -> {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          SecondaryButton(
            text = "Sebelumnya",
            onClick = { prevIndicatorProgress() }
          )
        }
      }

      isNextExist                -> {
        Box(
          modifier = Modifier.fillMaxWidth(),
          contentAlignment = Alignment.CenterEnd
        ) {
          PrimaryButton(
            text = "Berikutnya",
            onClick = { nextIndicatorProgress() }
          )
        }
      }
    }
  }
}

@Composable
private fun ColumnTextField(
  label : String,
  value : String,
  text : String,
  onValueChange : (String) -> Unit,
  placeholder : String,
  modifier : Modifier = Modifier
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(12.dp),
    modifier = modifier
  ) {
    Text(
      text = text,
      style = StyledText.MobileSmallMedium,
      color = ColorPalette.PrimaryColor700
    )
    CustomOutlinedTextField(
      label = label,
      value = value,
      onValueChange = onValueChange,
      placeholder = placeholder,
      modifier = Modifier.fillMaxWidth(),
      labelDefaultColor = ColorPalette.Monochrome400,
    )
  }
}


@Composable
private fun CustomRowWithFields(
  dropdownLabel : String,
  dropdownValue : String,
  onDropdownValueChange : (String) -> Unit,
  dropdownPlaceholder : String,
  dropdownItems : List<String>,
  expanded : Boolean,
  onExpandedChange : (Boolean) -> Unit,
  jumlahValue : String,
  onJumlahValueChange : (String) -> Unit,
  modifier : Modifier = Modifier
) {
  Row(
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    modifier = modifier.fillMaxWidth(),
    verticalAlignment = Alignment.Top
  ) {
    CustomOutlinedTextFieldDropdown(
      label = dropdownLabel,
      value = dropdownValue,
      onValueChange = onDropdownValueChange,
      placeholder = dropdownPlaceholder,
      modifier = Modifier.weight(0.7f),
      labelDefaultColor = ColorPalette.Monochrome400,
      dropdownItems = dropdownItems,
      expanded = expanded,
      onChangeExpanded = onExpandedChange
    )
    CustomOutlinedTextFieldJumlah(
      value = jumlahValue,
      onValueChange = onJumlahValueChange,
      placeholder = "Jumlah",
      modifier = Modifier.weight(0.3f)
    )
  }
}


@Composable
private fun CustomOutlinedTextFieldJumlah(
  value : String,
  onValueChange : (String) -> Unit,
  placeholder : String,
  modifier : Modifier = Modifier,
  size : Dp = 62.dp,
  textStyle : TextStyle = StyledText.MobileSmallRegular,
  borderColor : Color = ColorPalette.Monochrome400,
  focusedBorderColor : Color = ColorPalette.PrimaryColor700
) {
  OutlinedTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier.size(size),
    shape = RoundedCornerShape(40.dp),
    textStyle = textStyle.copy(
      textAlign = TextAlign.Center,
      lineHeight = size.value.sp
    ),
    placeholder = {
      Text(
        text = placeholder,
        style = textStyle.copy(
          color = ColorPalette.Monochrome400,
          textAlign = TextAlign.Center,
          lineHeight = size.value.sp
        ),
        modifier = Modifier.fillMaxWidth()
      )
    },
    colors = OutlinedTextFieldDefaults.colors(
      unfocusedBorderColor = borderColor,
      focusedBorderColor = focusedBorderColor,
      disabledBorderColor = borderColor,
      unfocusedLabelColor = borderColor,
      focusedLabelColor = focusedBorderColor
    ),
    singleLine = true
  )
}

