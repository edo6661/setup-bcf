package com.example.slicingbcf.implementation.auth.registrasi

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slicingbcf.data.local.model.Role
import com.example.slicingbcf.data.local.model.User
import com.example.slicingbcf.data.repo.user.UserRepository
import com.example.slicingbcf.domain.model.JangkauanPenerimaManfaat
import com.example.slicingbcf.domain.validator.isBlankOrEmpty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrasiViewModel @Inject constructor(
  private val userRepository : UserRepository
) : ViewModel() {

  private val _uiState = MutableStateFlow(RegistrasiState())
  val uiState = _uiState.asStateFlow()


  private fun onSubmit() {
    if (! validate()) {
      val user = User(
        namaLembaga = _uiState.value.namaLembaga,
        emailLembaga = _uiState.value.emailLembaga,
        alamatLembaga = _uiState.value.alamatLembaga,
        provinsi = _uiState.value.provinsi,
        kota = _uiState.value.kota,
        tanggalBerdiri = _uiState.value.selectedDate,
        jenisLembagaSosial = _uiState.value.jenisLembagaSosial,
        jenisClusterLembagaSosial = _uiState.value.jenisLembagaSosial,
        fokusIsu = _uiState.value.fokusIsu,
        profilSingkatLembaga = _uiState.value.profilLembaga,
        alasanMengikutiLead = _uiState.value.alasanKeikutsertaan,
        dokumenProfilPerusahaan = _uiState.value.selectedFileUriLaporanAkhirTahun.toString(),
        jangkauanProgram = _uiState.value.jangkauanProgram,
        wilayahJangkauanProgram = _uiState.value.wilayahJangkauanProgram,
        jumlahAngkaPenerimaanManfaat = _uiState.value.jumlahAngkaPenerimaanManfaat,
        targetUtamaProgram = _uiState.value.targetUtamaProgram,
        proposalProgramMitra = _uiState.value.selectedFileUriProposalProgram.toString(),
        namaPeserta = _uiState.value.namaLengkapPeserta,
        posisi = _uiState.value.posisiPeserta,
        pendidikanTerakhir = _uiState.value.pendidikanTerakhir,
        jenisKelamin = _uiState.value.jenisKelamin,
        nomorWhatsapp = _uiState.value.nomorWhatsappPeserta,
        emailPeserta = _uiState.value.emailPeserta,
        password = _uiState.value.namaLengkapPeserta + "123",
        ktp = _uiState.value.selectedFileUriKTP.toString(),
        cv = _uiState.value.selectedFileUriCV.toString(),
        adaPengurusLainYangAkanDiikutSertakanSebagaiPeserta = _uiState.value.adaPengurusLain,
        alasanMengikutiAgenda = _uiState.value.alasanTidakMengikutiAgenda,
        pernahMengikutiPelatihan = _uiState.value.pernahMengikutiPelatihanDesainProgram == "Sudah",
        darimanaMengetahuiLead = _uiState.value.sumberInformasiLEADAsString,
        yangDiketahuiTerkaitDesainProgram = _uiState.value.pengetahuanDesainProgram,
        yangDiketahuiTerkaitKeberlanjutan = _uiState.value.pengetahuanSustainability,
        yangDiketahuiTerkaitLaporanSosial = _uiState.value.pengetahuanSocialReport,
        laporanAkhirTahun = _uiState.value.selectedFileUriLaporanAkhirTahun.toString(),
        ekspetasiMengikutiLead = _uiState.value.ekspetasiSetelahLEAD,
        halYangInginDitanyakanKeLead = _uiState.value.halLainYangInginDisampaikan,
        umpanBalik = _uiState.value.halLainYangInginDisampaikan,
        pengalamanMendaftarLead = _uiState.value.halLainYangInginDisampaikan,
        role = Role.PESERTA.name
      )
      _uiState.update {
        it.copy(
          error = "Field yang wajib diisi tidak boleh kosong",
        )
      }
      return
    }

    _uiState.update { it.copy(isLoading = true) }

    viewModelScope.launch {
      try {
        val user = User(
          namaLembaga = _uiState.value.namaLembaga,
          emailLembaga = _uiState.value.emailLembaga,
          alamatLembaga = _uiState.value.alamatLembaga,
          provinsi = _uiState.value.provinsi,
          kota = _uiState.value.kota,
          tanggalBerdiri = _uiState.value.selectedDate,
          jenisLembagaSosial = _uiState.value.jenisLembagaSosial,
          jenisClusterLembagaSosial = _uiState.value.jenisLembagaSosial,
          fokusIsu = _uiState.value.fokusIsu,
          profilSingkatLembaga = _uiState.value.profilLembaga,
          alasanMengikutiLead = _uiState.value.alasanKeikutsertaan,
          dokumenProfilPerusahaan = _uiState.value.selectedFileUriLaporanAkhirTahun.toString(),
          jangkauanProgram = _uiState.value.jangkauanProgram,
          wilayahJangkauanProgram = _uiState.value.wilayahJangkauanProgram,
          jumlahAngkaPenerimaanManfaat = _uiState.value.jumlahAngkaPenerimaanManfaat,
          targetUtamaProgram = _uiState.value.targetUtamaProgram,
          proposalProgramMitra = _uiState.value.selectedFileUriProposalProgram.toString(),
          namaPeserta = _uiState.value.namaLengkapPeserta,
          posisi = _uiState.value.posisiPeserta,
          pendidikanTerakhir = _uiState.value.pendidikanTerakhir,
          jenisKelamin = _uiState.value.jenisKelamin,
          nomorWhatsapp = _uiState.value.nomorWhatsappPeserta,
          emailPeserta = _uiState.value.emailPeserta,
          password = _uiState.value.namaLengkapPeserta + "123",
          ktp = _uiState.value.selectedFileUriKTP.toString(),
          cv = _uiState.value.selectedFileUriCV.toString(),
          adaPengurusLainYangAkanDiikutSertakanSebagaiPeserta = _uiState.value.adaPengurusLain,
          alasanMengikutiAgenda = _uiState.value.alasanTidakMengikutiAgenda,
          pernahMengikutiPelatihan = _uiState.value.pernahMengikutiPelatihanDesainProgram == "Sudah",
          darimanaMengetahuiLead = _uiState.value.sumberInformasiLEAD.toString(),
          yangDiketahuiTerkaitDesainProgram = _uiState.value.pengetahuanDesainProgram,
          yangDiketahuiTerkaitKeberlanjutan = _uiState.value.pengetahuanSustainability,
          yangDiketahuiTerkaitLaporanSosial = _uiState.value.pengetahuanSocialReport,
          laporanAkhirTahun = _uiState.value.selectedFileUriLaporanAkhirTahun.toString(),
          ekspetasiMengikutiLead = _uiState.value.ekspetasiSetelahLEAD,
          halYangInginDitanyakanKeLead = _uiState.value.halLainYangInginDisampaikan,
          umpanBalik = _uiState.value.halLainYangInginDisampaikan,
          pengalamanMendaftarLead = _uiState.value.halLainYangInginDisampaikan,
          role = Role.PESERTA.name
        )
        Log.d("RegistrasiViewModel", "onSubmit Success: $user")

        userRepository.insertUser(user)

        _uiState.update {
          it.copy(
            isSuccess = true,
            isLoading = false,
            message = "Registrasi berhasil"
          )
        }

      } catch (e : Exception) {
        _uiState.update {
          it.copy(
            isLoading = false,
            error = e.message ?: "Terjadi kesalahan saat registrasi"
          )
        }
      }
    }
  }

  fun onEvent(event : RegisterEvent) {
    when (event) {
      is RegisterEvent.NamaLembagaChanged                                    -> onChangeNamaLembaga(
        event.namaLembaga
      )


      is RegisterEvent.ReadAndUnderstandCheckedChanged                       -> onReadAndUnderstandCheckedChanged(
        event.isChecked
      )

      is RegisterEvent.ConfirmInformationCheckedChanged                      -> onConfirmInformationCheckedChanged(
        event.isChecked
      )

      is RegisterEvent.MiniTrainingCheckedChanged                            -> onMiniTrainingCheckedChanged(
        event.isChecked
      )

      is RegisterEvent.InitialMentoringCheckedChanged                        -> onInitialMentoringCheckedChanged(
        event.isChecked
      )

      is RegisterEvent.PendampinganIntensifCheckedChanged                    -> onPendampinganIntensifCheckedChanged(
        event.isChecked
      )

      is RegisterEvent.SelectedDateChanged                                   -> onChangeSelectedDate(
        event.selectedDate
      )

      is RegisterEvent.EmailLembagaChanged                                   -> onChangeEmailLembaga(
        event.emailLembaga
      )

      is RegisterEvent.AlamatLembagaChanged                                  -> onChangeAlamatLembaga(
        event.alamatLembaga
      )

      is RegisterEvent.ProvinsiChanged                                       -> onChangeProvinsi(
        event.provinsi
      )

      is RegisterEvent.KotaChanged                                           -> onChangeKota(event.kota)
      is RegisterEvent.JenisLembagaSosialChanged                             -> onChangeJenisLembagaSosial(
        event.jenisLembagaSosial
      )

      is RegisterEvent.JenisClusterLembagaSosialChanged                      -> onChangeClusterJenisLembagaSosial(
        event.jenisLembagaSosial
      )

      is RegisterEvent.FokusIsuChanged                                       -> onChangeFokusIsu(
        event.fokusIsu
      )

      is RegisterEvent.ProfilLembagaChanged                                  -> onChangeProfilLembaga(
        event.profilLembaga
      )

      is RegisterEvent.AlasanKeikutsertaanChanged                            -> onChangeAlasanKeikutsertaan(
        event.alasanKeikutsertaan
      )

      is RegisterEvent.SelectedFileUriDokumentasiSesiMentoringClusterChanged -> onChangeSelectedFileUriDokumentasiSesiMentoringCluster(
        event.uri
      )

      is RegisterEvent.JangkauanProgramChanged                               -> onChangeJangkauanProgram(
        event.jangkauanProgram
      )

      is RegisterEvent.WilayahJangkauanProgramChanged                        -> onChangeWilayahJangkauanProgram(
        event.wilayahJangkauanProgram
      )


      is RegisterEvent.UpdateJumlahAngkaPenerimaanManfaat                    -> {
        onChangeJumlahAngkaPenerimaanManfaat(event.index, event.field)
      }

      is RegisterEvent.RemoveLastJumlahAngkaPenerimaanManfaat                -> {
        onRemoveLastJumlahAngkaPenerimaanManfaat()
      }

      is RegisterEvent.AddJumlahAngkaPenerimaanManfaat                       -> {
        onAddJumlahAngkaPenerimaanManfaat(event.newField)
      }


      is RegisterEvent.TargetUtamaProgramChanged                             -> onChangeTargetUtamaProgram(
        event.targetUtamaProgram
      )

      is RegisterEvent.SelectedFileUriProposalProgramChanged                 -> onChangeSelectedFileUriProposalProgram(
        event.uri
      )

      is RegisterEvent.NamaLengkapPesertaChanged                             -> onChangeNamaLengkapPeserta(
        event.namaLengkap
      )

      is RegisterEvent.PosisiPesertaChanged                                  -> onChangePosisiPeserta(
        event.posisiPeserta
      )

      is RegisterEvent.PendidikanTerakhirChanged                             -> onChangePendidikanTerakhir(
        event.pendidikanTerakhir
      )

      is RegisterEvent.JurusanPendidikanTerakhirChanged                      -> onChangeJurusanPendidikanTerakhir(
        event.jurusanPendidikan
      )

      is RegisterEvent.JenisKelaminChanged                                   -> onChangeJenisKelamin(
        event.jenisKelamin
      )

      is RegisterEvent.NomorWhatsappPesertaChanged                           -> onChangeNomorWhatsappPeserta(
        event.nomorWhatsapp
      )

      is RegisterEvent.EmailPesertaChanged                                   -> onChangeEmailPeserta(
        event.email
      )

      is RegisterEvent.SelectedFileUriKTPChanged                             -> onChangeSelectedFileUriKTP(
        event.uri
      )

      is RegisterEvent.SelectedFileUriCVChanged                              -> onChangeSelectedFileUriCV(
        event.uri
      )

      is RegisterEvent.AdaPengurusLainChanged                                -> onChangeAdaPengurusLain(
        event.adaPengurusLain
      )

      is RegisterEvent.AlasanTidakMengikutiAgendaChanged                     -> onChangeAlasanTidakMengikutiAgenda(
        event.alasan
      )

      is RegisterEvent.BersediaMengikutiAgendaChanged                        -> onChangeBersediaMengikutiAgenda(
        event.bersedia
      )

      is RegisterEvent.PernahMengikutiPelatihanDesainProgramChanged          -> onChangePernahMengikutiPelatihanDesainProgram(
        event.pelatihan
      )

      is RegisterEvent.SumberInformasiLEADChanged                            -> onChangeSumberInformasiLEAD(
        event.sumber,
        event.isChecked
      )

      is RegisterEvent.PengetahuanLeadChanged                                -> onChangePengetahuanLead(
        event.pengetahuan
      )

      is RegisterEvent.PengetahuanDesainProgramChanged                       -> onChangePengetahuanDesainProgram(
        event.pengetahuan
      )

      is RegisterEvent.PengetahuanSustainabilityChanged                      -> onChangePengetahuanSustainability(
        event.pengetahuan
      )

      is RegisterEvent.PengetahuanSocialReportChanged                        -> onChangePengetahuanSocialReport(
        event.pengetahuan
      )

      is RegisterEvent.SelectedFileUriLaporanAkhirTahunChanged               -> onChangeSelectedFileUriLaporanAkhirTahun(
        event.uri
      )

      is RegisterEvent.EkspetasiSetelahLEADChanged                           -> onChangeEkspetasiSetelahLEAD(
        event.ekspetasi
      )

      is RegisterEvent.HalLainYangInginDisampaikanChanged                    -> onChangeHalLainYangInginDisampaikan(
        event.halLain
      )

      is RegisterEvent.Submit                                                -> onSubmit()
      is RegisterEvent.ClearState                                            -> onClear()
    }
  }


  private fun onClear() {
    _uiState.value = RegistrasiState()
  }

  private fun validate() : Boolean {
    val state = _uiState.value

    val validations = listOf(
      state.namaLembaga to "Nama lembaga tidak boleh kosong",
      state.emailLembaga to "Email lembaga tidak boleh kosong",
      state.alamatLembaga to "Alamat lembaga tidak boleh kosong",
      state.provinsi to "Provinsi tidak boleh kosong",
      state.kota to "Kota tidak boleh kosong",
      state.jenisLembagaSosial to "Jenis lembaga sosial tidak boleh kosong",
      state.fokusIsu to "Fokus isu tidak boleh kosong",
      state.profilLembaga to "Profil lembaga tidak boleh kosong",
      state.alasanKeikutsertaan to "Alasan keikutsertaan tidak boleh kosong",
      state.jangkauanProgram to "Jangkauan program tidak boleh kosong",
      state.wilayahJangkauanProgram to "Wilayah jangkauan program tidak boleh kosong",
      state.targetUtamaProgram to "Target utama program tidak boleh kosong",
      state.namaLengkapPeserta to "Nama lengkap peserta tidak boleh kosong",
      state.posisiPeserta to "Posisi peserta tidak boleh kosong",
      state.pendidikanTerakhir to "Pendidikan terakhir tidak boleh kosong",
      state.jurusanPendidikanTerakhir to "Jurusan pendidikan terakhir tidak boleh kosong",
      state.jenisKelamin to "Jenis kelamin tidak boleh kosong",
      state.nomorWhatsappPeserta to "Nomor WhatsApp peserta tidak boleh kosong",
      state.emailPeserta to "Email peserta tidak boleh kosong",
      state.alasanTidakMengikutiAgenda to "Alasan tidak mengikuti agenda tidak boleh kosong",
      state.ekspetasiSetelahLEAD to "Ekspetasi setelah LEAD tidak boleh kosong"
    )

    val errorMap = validations.mapNotNull { (field, errorMessage) ->
      if (field.isBlankOrEmpty()) errorMessage else null
    }

    _uiState.update { it ->
      it.copy(
        namaLembagaError = errorMap.find { it == "Nama lembaga tidak boleh kosong" },
        emailLembagaError = errorMap.find { it == "Email lembaga tidak boleh kosong" },
        alamatLembagaError = errorMap.find { it == "Alamat lembaga tidak boleh kosong" },
        provinsiError = errorMap.find { it == "Provinsi tidak boleh kosong" },
        kotaError = errorMap.find { it == "Kota tidak boleh kosong" },
        jenisLembagaSosialError = errorMap.find { it == "Jenis lembaga sosial tidak boleh kosong" },
        fokusIsuError = errorMap.find { it == "Fokus isu tidak boleh kosong" },
        profilLembagaError = errorMap.find { it == "Profil lembaga tidak boleh kosong" },
        alasanKeikutsertaanError = errorMap.find { it == "Alasan keikutsertaan tidak boleh kosong" },
        jangkauanProgramError = errorMap.find { it == "Jangkauan program tidak boleh kosong" },
        wilayahJangkauanProgramError = errorMap.find { it == "Wilayah jangkauan program tidak boleh kosong" },
        targetUtamaProgramError = errorMap.find { it == "Target utama program tidak boleh kosong" },
        namaLengkapPesertaError = errorMap.find { it == "Nama lengkap peserta tidak boleh kosong" },
        posisiPesertaError = errorMap.find { it == "Posisi peserta tidak boleh kosong" },
        pendidikanTerakhirError = errorMap.find { it == "Pendidikan terakhir tidak boleh kosong" },
        jurusanPendidikanTerakhirError = errorMap.find { it == "Jurusan pendidikan terakhir tidak boleh kosong" },
        jenisKelaminError = errorMap.find { it == "Jenis kelamin tidak boleh kosong" },
        nomorWhatsappPesertaError = errorMap.find { it == "Nomor WhatsApp peserta tidak boleh kosong" },
        emailPesertaError = errorMap.find { it == "Email peserta tidak boleh kosong" },
        alasanTidakMengikutiAgendaError = errorMap.find { it == "Alasan tidak mengikuti agenda tidak boleh kosong" },
        ekspetasiSetelahLEADError = errorMap.find { it == "Ekspetasi setelah LEAD tidak boleh kosong" }
      )
    }

    return errorMap.isEmpty()
  }


  private fun onChangeNamaLembaga(namaLembaga : String) {
    _uiState.update { it.copy(namaLembaga = namaLembaga) }
  }

  private fun onReadAndUnderstandCheckedChanged(isChecked : Boolean) {
    _uiState.update { it.copy(readAndUnderstandChecked = isChecked) }
  }

  private fun onConfirmInformationCheckedChanged(isChecked : Boolean) {
    _uiState.update { it.copy(confirmInformationChecked = isChecked) }
  }

  private fun onMiniTrainingCheckedChanged(isChecked : Boolean) {
    _uiState.update { it.copy(miniTrainingChecked = isChecked) }
  }

  private fun onInitialMentoringCheckedChanged(isChecked : Boolean) {
    _uiState.update { it.copy(initialMentoringChecked = isChecked) }
  }

  private fun onPendampinganIntensifCheckedChanged(isChecked : Boolean) {
    _uiState.update { it.copy(pendampinganIntensifChecked = isChecked) }
  }

  private fun onChangeSelectedDate(selectedDate : String) {
    _uiState.update { it.copy(selectedDate = selectedDate) }
  }

  private fun onChangeEmailLembaga(emailLembaga : String) {
    _uiState.update { it.copy(emailLembaga = emailLembaga) }
  }

  private fun onChangeAlamatLembaga(alamatLembaga : String) {
    _uiState.update { it.copy(alamatLembaga = alamatLembaga) }
  }

  private fun onChangeProvinsi(provinsi : String) {
    _uiState.update { it.copy(provinsi = provinsi) }
  }

  private fun onChangeKota(kota : String) {
    _uiState.update { it.copy(kota = kota) }
  }

  private fun onChangeJenisLembagaSosial(jenisLembagaSosial : String) {
    _uiState.update { it.copy(jenisLembagaSosial = jenisLembagaSosial) }
  }

  private fun onChangeClusterJenisLembagaSosial(jenisClusterLembagaSosial : String) {
    _uiState.update { it.copy(jenisClusterLembagaSosial = jenisClusterLembagaSosial) }
  }

  private fun onChangeFokusIsu(fokusIsu : String) {
    _uiState.update { it.copy(fokusIsu = fokusIsu) }
  }

  private fun onChangeProfilLembaga(profilLembaga : String) {
    _uiState.update { it.copy(profilLembaga = profilLembaga) }
  }

  private fun onChangeAlasanKeikutsertaan(alasanKeikutsertaan : String) {
    _uiState.update { it.copy(alasanKeikutsertaan = alasanKeikutsertaan) }
  }

  private fun onChangeSelectedFileUriDokumentasiSesiMentoringCluster(uri : Uri?) {
    _uiState.update { it.copy(selectedFileUriDokumentasiSesiMentoringCluster = uri) }
  }

  private fun onChangeJangkauanProgram(jangkauanProgram : String) {
    _uiState.update { it.copy(jangkauanProgram = jangkauanProgram) }
  }

  private fun onChangeWilayahJangkauanProgram(wilayahJangkauanProgram : String) {
    _uiState.update { it.copy(wilayahJangkauanProgram = wilayahJangkauanProgram) }
  }

  private fun onChangeJumlahAngkaPenerimaanManfaat(
    index : Int,
    updatedField : JangkauanPenerimaManfaat
  ) {
    _uiState.update { currentState ->
      currentState.copy(
        jumlahAngkaPenerimaanManfaat = currentState.jumlahAngkaPenerimaanManfaat.mapIndexed { i, field ->
          if (i == index) updatedField else field
        }
      )
    }
  }


  private fun onRemoveLastJumlahAngkaPenerimaanManfaat() {
    _uiState.update { currentState ->
      currentState.copy(
        jumlahAngkaPenerimaanManfaat = currentState.jumlahAngkaPenerimaanManfaat.dropLast(1)
      )
    }
  }

  private fun onAddJumlahAngkaPenerimaanManfaat(
    newField : JangkauanPenerimaManfaat
  ) {
    _uiState.update { currentState ->
      currentState.copy(
        jumlahAngkaPenerimaanManfaat = currentState.jumlahAngkaPenerimaanManfaat + newField
      )
    }

  }


  private fun onChangeTargetUtamaProgram(targetUtamaProgram : String) {
    _uiState.update { it.copy(targetUtamaProgram = targetUtamaProgram) }
  }

  private fun onChangeSelectedFileUriProposalProgram(uri : Uri?) {
    _uiState.update { it.copy(selectedFileUriProposalProgram = uri) }
  }

  private fun onChangeNamaLengkapPeserta(namaLengkap : String) {
    _uiState.update { it.copy(namaLengkapPeserta = namaLengkap) }
  }

  private fun onChangePosisiPeserta(posisiPeserta : String) {
    _uiState.update { it.copy(posisiPeserta = posisiPeserta) }
  }

  private fun onChangePendidikanTerakhir(pendidikanTerakhir : String) {
    _uiState.update { it.copy(pendidikanTerakhir = pendidikanTerakhir) }
  }

  private fun onChangeJurusanPendidikanTerakhir(jurusanPendidikan : String) {
    _uiState.update { it.copy(jurusanPendidikanTerakhir = jurusanPendidikan) }
  }

  private fun onChangeJenisKelamin(jenisKelamin : String) {
    _uiState.update { it.copy(jenisKelamin = jenisKelamin) }
  }

  private fun onChangeNomorWhatsappPeserta(nomorWhatsapp : String) {
    _uiState.update { it.copy(nomorWhatsappPeserta = nomorWhatsapp) }
  }

  private fun onChangeEmailPeserta(email : String) {
    _uiState.update { it.copy(emailPeserta = email) }
  }

  private fun onChangeSelectedFileUriKTP(uri : Uri?) {
    _uiState.update { it.copy(selectedFileUriKTP = uri) }
  }

  private fun onChangeSelectedFileUriCV(uri : Uri?) {
    _uiState.update { it.copy(selectedFileUriCV = uri) }
  }

  private fun onChangeAdaPengurusLain(adaPengurusLain : Boolean) {
    _uiState.update { it.copy(adaPengurusLain = adaPengurusLain) }
  }

  private fun onChangeAlasanTidakMengikutiAgenda(alasan : String) {
    _uiState.update { it.copy(alasanTidakMengikutiAgenda = alasan) }
  }

  private fun onChangeBersediaMengikutiAgenda(bersedia : Boolean) {
    _uiState.update { it.copy(bersediaMengikutiAgenda = bersedia) }
  }

  private fun onChangePernahMengikutiPelatihanDesainProgram(pelatihan : String) {
    _uiState.update { it.copy(pernahMengikutiPelatihanDesainProgram = pelatihan) }
  }

  private fun onChangeSumberInformasiLEAD(sumber : String, isChecked : Boolean) {
    val updatedSumberInformasiLEAD = if (isChecked) {
      _uiState.value.sumberInformasiLEAD + sumber
    } else {
      _uiState.value.sumberInformasiLEAD - sumber
    }
    _uiState.value = _uiState.value.copy(sumberInformasiLEAD = updatedSumberInformasiLEAD)

  }

  private fun onChangePengetahuanDesainProgram(pengetahuan : String) {
    _uiState.update { it.copy(pengetahuanDesainProgram = pengetahuan) }
  }

  private fun onChangePengetahuanLead(pengetahuan : String) {
    _uiState.update { it.copy(pengetahuanLead = pengetahuan) }
  }

  private fun onChangePengetahuanSustainability(pengetahuan : String) {
    _uiState.update { it.copy(pengetahuanSustainability = pengetahuan) }
  }

  private fun onChangePengetahuanSocialReport(pengetahuan : String) {
    _uiState.update { it.copy(pengetahuanSocialReport = pengetahuan) }
  }

  private fun onChangeSelectedFileUriLaporanAkhirTahun(uri : Uri?) {
    _uiState.update { it.copy(selectedFileUriLaporanAkhirTahun = uri) }
  }

  private fun onChangeEkspetasiSetelahLEAD(ekspetasi : String) {
    _uiState.update { it.copy(ekspetasiSetelahLEAD = ekspetasi) }
  }

  private fun onChangeHalLainYangInginDisampaikan(halLain : String) {
    _uiState.update { it.copy(halLainYangInginDisampaikan = halLain) }
  }


}
