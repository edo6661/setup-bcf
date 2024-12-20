package com.example.slicingbcf.ui.navigation

sealed class Screen(val route : String) {

  object Home : Screen("home")

  sealed class Auth(route : String) : Screen(route) {
    object Login : Auth("login")
    object ForgotPassword : Auth("forgot-password")
    object Registrasi : Peserta("registrasi")
    object UmpanBalikRegistrasi : Auth("umpan-balik-registrasi")
  }


  sealed class Peserta(route : String) : Screen(route) {
    object DataPeserta : Peserta("data-peserta")
    object KelompokMentoring : Peserta("kelompok-mentoring")
    object PengumumanPeserta : Peserta("pengumuman-peserta")
    data class DetailPengumumanPeserta(val id : String) : Peserta("pengumuman-peserta/$id")
    object WorksheetPeserta : Peserta("worksheet-peserta")
    data class DetailWorksheetPeserta(val id : String) : Peserta("worksheet-peserta/$id")
    object Pengaturan : Peserta("pengaturan-peserta")
    object PusatInformasi : Mentor("pusat-informasi")
    object SearchPusatInformasi : Mentor("search-pusat-informasi")
    data class DetailPusatInformasi(val id : String) : Mentor("pusat-informasi/$id")

    object PenilaianPeserta : Peserta("penilaian-peserta")
    object FormMonthlyReport : Peserta("form-monthly-report")
    data class DetailFormMonthlyReport(val id : String) : Peserta("form-monthly-report/$id")
    object CheckStatusRegistrasi : Peserta("check-status-registrasi")

  }

  sealed class Mentor(route : String) : Screen(route) {
    object PenilaianPeserta : Mentor("penilaian-peserta-mentor")
    data class DetailPenilaianPeserta(val id : String) : Mentor("penilaian-peserta-mentor/$id")
    object FeedbackPeserta : Mentor("feedback-peserta")
    object Pitchdeck : Mentor("pitchdeck")
    data class DetailPitchdeck(val id : String) : Mentor("pitchdeck/$id")
    data class MoreDetailPitchdeck(val id : String) : Mentor("pitchdeck/$id/more")
    object ForumDiskusi : Mentor("forum-diskusi")
    object SearchForumDiskusi : Mentor("search-forum-diskusi")
    data class DetailForumDiskusi(val id : String) : Mentor("forum-diskusi/$id")
    object DataPeserta : Mentor("data-peserta-mentor")
    object Pengaturan : Mentor("pengaturan-peserta")
    object Pengumuman : Mentor("pengumuman")
    data class DetailPengumumanPeserta(val id : String) : Peserta("pengumuman-mentor/$id")
    object KelompokMentoring : Mentor("kelompok-mentoring")


  }


}
