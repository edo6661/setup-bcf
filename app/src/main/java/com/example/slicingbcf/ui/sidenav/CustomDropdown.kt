package com.example.slicingbcf.ui.sidenav

import android.util.Log
import com.example.slicingbcf.ui.navigation.Screen

data class DropdownItem(
  val text : String,
  val onClick : () -> Unit,
  val route : String?
)

fun dropdownItemsPendaftaran(
  navigateAndCloseSideNav : (String) -> Unit
) : List<DropdownItem> {
  return listOf(
    DropdownItem(
      text = "Registrasi Peserta",
      onClick = {
        Log.d("SideNav", "Registrasi Peserta clicked")
      },
      route = null
    ),
    DropdownItem(
      text = "Cek Status Peserta",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.CheckStatusRegistrasi.route)
      },
      route = null
    )
  )
}

fun dropdownItemsPeserta(
  navigateAndCloseSideNav : (String) -> Unit
) : List<DropdownItem> {
  return listOf(
    DropdownItem(
      text = "Pusat Informasi",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.PusatInformasi.route)
      },
      route = Screen.Peserta.PusatInformasi.route
    ),
    DropdownItem(
      text = "Data Peserta",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.DataPeserta.route)
      },
      route = Screen.Peserta.DataPeserta.route
    ),

    DropdownItem(
      text = "Kelompok Mentoring",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.KelompokMentoring.route)
      },
      route = Screen.Peserta.KelompokMentoring.route
    ),
    DropdownItem(
      text = "Pengumuman",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.PengumumanPeserta.route)
      },
      route = Screen.Peserta.PengumumanPeserta.route
    ),

    DropdownItem(
      text = "Pengaturan",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.Pengaturan.route)
      },
      route = Screen.Peserta.Pengaturan.route
    ),
    DropdownItem(
      text = "Worksheet Peserta",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.WorksheetPeserta.route)
      },
      route = Screen.Peserta.WorksheetPeserta.route
    ),
    DropdownItem(
      text = "Penilaian Peserta",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.PenilaianPeserta.route)
      },
      route = Screen.Peserta.PenilaianPeserta.route
    ),
    DropdownItem(
      text = "Form Monthly Report",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.FormMonthlyReport.route)
      },
      route = Screen.Peserta.FormMonthlyReport.route
    ),
  )
}

fun dropdownItemsMentor(
  navigateAndCloseSideNav : (String) -> Unit
) : List<DropdownItem> {
  return listOf(
    DropdownItem(
      text = "Kelompok Mentor",
      onClick = {
        Log.d("SideNav", "Kelompok Mentor clicked")
      },
      route = null
    ),
    DropdownItem(
      text = "Umpan Balik Mentor",
      onClick = {
        Log.d("SideNav", "Umpan Balik Mentor clicked")
      },
      route = null
    ),
    DropdownItem(
      text = "Pitchdeck",
      onClick = {
        navigateAndCloseSideNav(Screen.Mentor.Pitchdeck.route)
      },
      route = Screen.Mentor.Pitchdeck.route
    ),
    DropdownItem(
      text = "Forum Diskusi",
      onClick = {
        navigateAndCloseSideNav(Screen.Mentor.ForumDiskusi.route)
      },
      route = Screen.Mentor.ForumDiskusi.route
    ),
    DropdownItem(
      text = "Data Peserta",
      onClick = {
        navigateAndCloseSideNav(Screen.Mentor.DataPeserta.route)
      },
      route = Screen.Mentor.DataPeserta.route
    ),
    DropdownItem(
      text = "Penilaian Peserta",
      onClick = {
        navigateAndCloseSideNav(Screen.Mentor.PenilaianPeserta.route)
      },
      route = Screen.Mentor.PenilaianPeserta.route
    ),
    DropdownItem(
      text = "Feedback Peserta",
      onClick = {
        navigateAndCloseSideNav(Screen.Mentor.FeedbackPeserta.route)
      },
      route = Screen.Mentor.FeedbackPeserta.route
    ),
    DropdownItem(
      text = "Pengumuman",
      onClick = {
        navigateAndCloseSideNav(Screen.Mentor.Pengumuman.route)
      },
      route = Screen.Mentor.Pengumuman.route
    ),
    DropdownItem(
      text = "Pengaturan",
      onClick = {
        navigateAndCloseSideNav(Screen.Mentor.Pengaturan.route)
      },
      route = Screen.Mentor.Pengaturan.route
    ),
    DropdownItem(
      text = "Kelompok Mentoring",
      onClick = {
        navigateAndCloseSideNav(Screen.Mentor.KelompokMentoring.route)
      },
      route = Screen.Mentor.KelompokMentoring.route
    ),
  )
}

fun dropdownItemsTugas(
  navigateAndCloseSideNav : (String) -> Unit
) : List<DropdownItem> {
  return listOf(
    DropdownItem(
      text = "Modul",
      onClick = {
        Log.d("SideNav", "Modul clicked")
      },
      route = null
    ),
    DropdownItem(
      text = "Laporan",
      onClick = {
        Log.d("SideNav", "Laporan clicked")
      },
      route = null
    ),
    DropdownItem(
      text = "Lembar Kerja",
      onClick = {
        Log.d("SideNav", "Lembar Kerja clicked")
      },
      route = null
    ),
    DropdownItem(
      text = "Lembar Kerja",
      onClick = {
        Log.d("SideNav", "Lembar Kerja clicked")
      },
      route = null
    ),
    DropdownItem(
      text = "Pitch Deck",
      onClick = {
        Log.d("SideNav", "Pitch Deck clicked")
      },
      route = null
    ),
  )
}

fun dropdownItemsKegiatan(
  navigateAndCloseSideNav : (String) -> Unit
) : List<DropdownItem> {
  return listOf(
    DropdownItem(
      text = "Jadwal Kegiatan",
      onClick = {
        Log.d("SideNav", "Jadwal Kegiatan clicked")
      },
      route = null
    ),
    DropdownItem(
      text = "Umpan Balik Kegiatan",
      onClick = {
        Log.d("SideNav", "Umpan Balik Kegiatan clicked")
      },
      route = null
    )
  )
}

//
//fun dropdownItemsPendaftaran(
//  navigateAndCloseSideNav : (String) -> Unit
//) : List<DropdownItem> {
//  return listOf(
//    DropdownItem(
//      text = "Registrasi Peserta",
//      onClick = {
//        Log.d("SideNav", "Registrasi Peserta clicked")
//      },
//      route = null
//    ),
//    DropdownItem(
//      text = "Cek Status Peserta",
//      onClick = {
//        Log.d("SideNav", "Cek Status Peserta clicked")
//      },
//      route = null
//    )
//  )
//}

// ! ROLE GUEST
fun dropdownItemsPeserta_Guest(
  navigateAndCloseSideNav : (String) -> Unit
) : List<DropdownItem> {
  return listOf(
    DropdownItem(
      text = "Pusat Informasi",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.PusatInformasi.route)
      },
      route = Screen.Peserta.PusatInformasi.route
    ),
    DropdownItem(
      text = "Data Peserta",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.DataPeserta.route)
      },
      route = Screen.Peserta.DataPeserta.route
    ),
    DropdownItem(
      text = "Penilaian Peserta",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.PenilaianPeserta.route)
      },
      route = Screen.Peserta.PenilaianPeserta.route
    ),
    DropdownItem(
      text = "Kelompok Mentoring",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.KelompokMentoring.route)
      },
      route = Screen.Peserta.KelompokMentoring.route
    ),
    DropdownItem(
      text = "Pengumuman",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.PengumumanPeserta.route)
      },
      route = Screen.Peserta.PengumumanPeserta.route
    ),
    DropdownItem(
      text = "Feedback Peserta",
      onClick = {
//        navigateAndCloseSideNav(Screen.Peserta.FeedbackPeserta.route)
      },
      route = Screen.Home.route
    ),
    DropdownItem(
      text = "Form Umpan Balik Mentor",
      onClick = {
//        navigateAndCloseSideNav(Screen.Peserta.FormFeedbackMentor.route)
      },
      route = Screen.Home.route
    ),

    DropdownItem(
      text = "Pengaturan",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.Pengaturan.route)
      },
      route = Screen.Peserta.Pengaturan.route
    ),
    DropdownItem(
      text = "Worksheet Peserta",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.WorksheetPeserta.route)
      },
      route = Screen.Peserta.WorksheetPeserta.route
    ),
  )
}

fun dropdownItemsMentor_Guest(
  navigateAndCloseSideNav : (String) -> Unit
) : List<DropdownItem> {
  return listOf(
    DropdownItem(
      text = "Kelompok Mentor",
      onClick = {
        Log.d("SideNav", "Kelompok Mentor clicked")
      },
      route = null
    ),
    DropdownItem(
      text = "Umpan Balik Mentor",
      onClick = {
        Log.d("SideNav", "Umpan Balik Mentor clicked")
      },
      route = null
    ),
    DropdownItem(
      text = "Form Umpan Balik Peserta",
      onClick = {
//        navigateAndCloseSideNav(Screen.Mentor.FormFeedbackPeserta.route)
      },
      route = Screen.Home.route
    ),
    DropdownItem(
      text = "Pitchdeck",
      onClick = {
        navigateAndCloseSideNav(Screen.Mentor.Pitchdeck.route)
      },
      route = Screen.Mentor.Pitchdeck.route
    ),
    DropdownItem(
      text = "Forum Diskusi",
      onClick = {
        navigateAndCloseSideNav(Screen.Mentor.ForumDiskusi.route)
      },
      route = Screen.Mentor.ForumDiskusi.route
    ),
    DropdownItem(
      text = "Data Peserta",
      onClick = {
        navigateAndCloseSideNav(Screen.Mentor.DataPeserta.route)
      },
      route = Screen.Mentor.DataPeserta.route
    ),
    DropdownItem(
      text = "Penilaian Peserta",
      onClick = {
        navigateAndCloseSideNav(Screen.Mentor.PenilaianPeserta.route)
      },
      route = Screen.Mentor.PenilaianPeserta.route
    )
  )
}

fun dropdownItemsTugas_Guest(
  navigateAndCloseSideNav : (String) -> Unit
) : List<DropdownItem> {
  return listOf(
    DropdownItem(
      text = "Modul",
      onClick = {
        Log.d("SideNav", "Modul clicked")
      },
      route = null
    ),
    DropdownItem(
      text = "Laporan",
      onClick = {
        Log.d("SideNav", "Laporan clicked")
      },
      route = null
    ),
    DropdownItem(
      text = "Lembar Kerja",
      onClick = {
        Log.d("SideNav", "Lembar Kerja clicked")
      },
      route = null
    ),
    DropdownItem(
      text = "Pitch Deck",
      onClick = {
//        navigateAndCloseSideNav(Screen.Tugas.PitchDeck.route)
      },
      route = Screen.Home.route
    ),
  )
}

fun dropdownItemsKegiatan_Guest(
  navigateAndCloseSideNav : (String) -> Unit
) : List<DropdownItem> {
  return listOf(
    DropdownItem(
      text = "Jadwal Kegiatan",
      onClick = {
        Log.d("SideNav", "Jadwal clicked")
//        navigateAndCloseSideNav(Screen.Kegiatan.JadwalBulanPeserta.route)
      },
      route = Screen.Home.route
    ),
    DropdownItem(
      text = "Jadwal Kegiatan (Mentor)",
      onClick = {
//        navigateAndCloseSideNav(Screen.Kegiatan.JadwalBulanMentor.route)
      },
      route = Screen.Home.route
    ),

    DropdownItem(
      text = "Umpan Balik Kegiatan",
      onClick = {
//        navigateAndCloseSideNav(Screen.Kegiatan.UmpanBalikKegiatan.route)
      },
      route = Screen.Home.route
    )
  )
}


// ! DROPDOWN ROLE PESERTA
fun dropdownItemsPendaftaran_Guest(
  navigateAndCloseSideNav : (String) -> Unit
) : List<DropdownItem> {
  return listOf(
    DropdownItem(
      text = "Registrasi Peserta",
      onClick = {
        navigateAndCloseSideNav(Screen.Auth.Registrasi.route)
      },
      route = Screen.Auth.Registrasi.route
    ),
    DropdownItem(
      text = "Cek Status Peserta",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.CheckStatusRegistrasi.route)
      },
      route = Screen.Peserta.CheckStatusRegistrasi.route
    )
  )
}

// ! DROPDOWN ROLE PESERTA
fun dropdownItemsPeserta_Peserta(
  navigateAndCloseSideNav : (String) -> Unit
) : List<DropdownItem> {
  return listOf(
    DropdownItem(
      text = "Data Peserta",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.DataPeserta.route)
      },
      route = Screen.Peserta.DataPeserta.route
    ),
    DropdownItem(
      text = "Umpan Balik Peserta",
      onClick = {
//        navigateAndCloseSideNav(Screen.Peserta.FeedbackPeserta.route)
      },
      route = Screen.Home.route
    ),
    DropdownItem(
      text = "Penilaian Peserta",
      onClick = {
        Log.d("SideNav", "Penilaian Peserta clicked")
      },
      route = null
    )
  )
}

fun dropdownItemsMentor_Peserta(
  navigateAndCloseSideNav : (String) -> Unit
) : List<DropdownItem> {
  return listOf(
    DropdownItem(
      text = "Kelompok Mentoring",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.KelompokMentoring.route)
      },
      route = Screen.Peserta.KelompokMentoring.route
    ),
    DropdownItem(
      text = "Umpan Balik Mentor",
      onClick = {
//        navigateAndCloseSideNav(Screen.Peserta.FormFeedbackMentor.route)
      },
      route = Screen.Home.route
    )
  )
}

fun dropdownItemsTugas_Peserta(
  navigateAndCloseSideNav : (String) -> Unit
) : List<DropdownItem> {
  return listOf(
    DropdownItem(
      text = "Modul",
      onClick = {
        Log.d("SideNav", "Modul clicked")
      },
      route = null
    ),
    DropdownItem(
      text = "Laporan",
      onClick = {
        Log.d("SideNav", "Laporan clicked")
      },
      route = null
    ),
    DropdownItem(
      text = "Lembar Kerja",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.WorksheetPeserta.route)
      },
      route = Screen.Peserta.WorksheetPeserta.route
    ),
    DropdownItem(
      text = "Pitch Deck",
      onClick = {
//        navigateAndCloseSideNav(Screen.Tugas.PitchDeck.route)
      },
      route = Screen.Home.route
    ),
  )
}

fun dropdownItemsKegiatan_Peserta(
  navigateAndCloseSideNav : (String) -> Unit
) : List<DropdownItem> {
  return listOf(
    DropdownItem(
      text = "Jadwal Kegiatan",
      onClick = {
        Log.d("SideNav", "Jadwal clicked")
//        navigateAndCloseSideNav(Screen.Kegiatan.JadwalBulanPeserta.route)
      },
      route = Screen.Home.route
    ),
    DropdownItem(
      text = "Umpan Balik Kegiatan",
      onClick = {
//        navigateAndCloseSideNav(Screen.Kegiatan.UmpanBalikKegiatan.route)
      },
      route = Screen.Home.route
    )

  )
}


// ! DROPDOWN ROLE MENTOR
fun dropdownItemsPeserta_Mentor(
  navigateAndCloseSideNav : (String) -> Unit
) : List<DropdownItem> {
  return listOf(
    DropdownItem(
      text = "Data Peserta",
      onClick = {
        navigateAndCloseSideNav(Screen.Mentor.DataPeserta.route)
      },
      route = Screen.Mentor.DataPeserta.route
    ),
    DropdownItem(
      text = "Umpan Balik Peserta",
      onClick = {
//        navigateAndCloseSideNav(Screen.Mentor.FormFeedbackPeserta.route)
      },
      route = Screen.Home.route
    ),
    DropdownItem(
      text = "Penilaian Peserta",
      onClick = {
        navigateAndCloseSideNav(Screen.Mentor.PenilaianPeserta.route)
      },
      route = Screen.Mentor.PenilaianPeserta.route
    )
  )
}

fun dropdownItemsMentor_Mentor(
  navigateAndCloseSideNav : (String) -> Unit
) : List<DropdownItem> {
  return listOf(
    DropdownItem(
      text = "Kelompok Mentoring",
      onClick = {
        navigateAndCloseSideNav(Screen.Peserta.KelompokMentoring.route)
      },
      route = Screen.Peserta.KelompokMentoring.route
    ),
    DropdownItem(
      text = "Umpan Balik Mentor",
      onClick = {
        Log.d("SideNav", "Umpan Balik Mentor clicked")
      },
      route = null
    ),
  )
}

fun dropdownItemsTugas_Mentor(
  navigateAndCloseSideNav : (String) -> Unit
) : List<DropdownItem> {
  return listOf(
    DropdownItem(
      text = "Modul",
      onClick = {
        Log.d("SideNav", "Modul clicked")
      },
      route = null
    ),
    DropdownItem(
      text = "Laporan",
      onClick = {
        Log.d("SideNav", "Laporan clicked")
      },
      route = null
    ),
    DropdownItem(
      text = "Lembar Kerja",
      onClick = {
        Log.d("SideNavMentor", "Lembar Kerja clicked")
      },
      route = null
    ),
    DropdownItem(
      text = "Pitch Deck",
      onClick = {
        navigateAndCloseSideNav(Screen.Mentor.Pitchdeck.route)
      },
      route = Screen.Mentor.Pitchdeck.route
    ),
  )
}

fun dropdownItemsKegiatan_Mentor(
  navigateAndCloseSideNav : (String) -> Unit
) : List<DropdownItem> {
  return listOf(
    DropdownItem(
      text = "Jadwal Kegiatan",
      onClick = {
//        navigateAndCloseSideNav(Screen.Kegiatan.JadwalBulanMentor.route)
      },
      route = Screen.Home.route
    )
  )
}