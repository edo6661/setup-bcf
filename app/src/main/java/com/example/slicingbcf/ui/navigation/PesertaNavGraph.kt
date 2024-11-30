package com.example.slicingbcf.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.slicingbcf.implementation.mentor.kelompok_mentoring_mentor.KelompokMentoringScreen
import com.example.slicingbcf.implementation.mentor.pengaturan_mentor.PengaturanScreen
import com.example.slicingbcf.implementation.peserta.check_status_registrasi.CheckStatusRegistrasiScreen
import com.example.slicingbcf.implementation.peserta.data_peserta.DataPesertaScreen
import com.example.slicingbcf.implementation.peserta.form_monthly_report.DetailFormMonthlyReportScreen
import com.example.slicingbcf.implementation.peserta.form_monthly_report.FormMonthlyReportScreen
import com.example.slicingbcf.implementation.peserta.pengumuman_peserta.DetailPengumumanPesertaScreen
import com.example.slicingbcf.implementation.peserta.pengumuman_peserta.PengumumanPesertaScreen
import com.example.slicingbcf.implementation.peserta.penilaian_peserta.PenilaianPesertaScreen
import com.example.slicingbcf.implementation.peserta.pusat_informasi.DetailPusatInformasiScreen
import com.example.slicingbcf.implementation.peserta.pusat_informasi.PusatInformasiScreen
import com.example.slicingbcf.implementation.peserta.worksheet_peserta.DetailWorksheetPesertaScreen
import com.example.slicingbcf.implementation.peserta.worksheet_peserta.WorksheetPesertaScreen


fun NavGraphBuilder.pesertaNavGraph(
  modifier : Modifier,
  navController : NavHostController
) {
  navigation(
    startDestination = Screen.Peserta.CheckStatusRegistrasi.route, route = "peserta"
  ) {
    composable(Screen.Peserta.DataPeserta.route) {
      DataPesertaScreen(
        modifier = modifier,
      )
    }
    composable(Screen.Peserta.KelompokMentoring.route) {
      KelompokMentoringScreen(
        modifier = modifier,
      )
    }
    composable(Screen.Peserta.PengumumanPeserta.route) {
      val onNavigateDetailPengumuman = { id : String ->
        navController.navigateSingleTop("pengumuman-peserta/$id")
      }
      PengumumanPesertaScreen(
        modifier = modifier,
        onNavigateDetailPengumuman = onNavigateDetailPengumuman
      )
    }
    composable(
      route = "pengumuman-peserta/{id}",
      arguments = listOf(navArgument("id") { type = NavType.StringType })
    ) { backStackEntry ->
      val id = backStackEntry.arguments?.getString("id") ?: ""
      if (id.isEmpty()) throw IllegalStateException("id must not be empty")
      DetailPengumumanPesertaScreen(
        modifier = modifier.padding(
        ), id = id
      )
    }
    composable(Screen.Peserta.WorksheetPeserta.route) {
      val onNavigateDetailWorksheetPeserta = { id : String ->
        navController.navigateSingleTop("worksheet-peserta/$id")
      }
      WorksheetPesertaScreen(
        modifier = modifier,
        onNavigateDetailWorksheetPeserta = onNavigateDetailWorksheetPeserta
      )
    }
    composable(
      route = "worksheet-peserta/{id}",
      arguments = listOf(navArgument("id") { type = NavType.StringType })
    ) { backStackEntry ->
      val id = backStackEntry.arguments?.getString("id") ?: ""
      if (id.isEmpty()) throw IllegalStateException("id must not be empty")
      DetailWorksheetPesertaScreen(
        modifier = modifier,
        id = id
      )
    }
    composable(
      route = Screen.Peserta.Pengaturan.route,
    ) {
      PengaturanScreen(
        modifier = modifier,
      )
    }
    composable(
      route = Screen.Peserta.PenilaianPeserta.route,
    ) {
      PenilaianPesertaScreen(
        modifier = modifier,
      )
    }
    composable(
      route = Screen.Peserta.CheckStatusRegistrasi.route,
    ) {
      CheckStatusRegistrasiScreen(
        modifier = modifier,
      )
    }
    composable(
      route = Screen.Peserta.PusatInformasi.route,
    ) {
      val onNavigateDetailPusatInformasi = { id : String ->
        navController.navigateSingleTop("pusat-informasi/$id")
      }
      PusatInformasiScreen(
        modifier = modifier,
        onNavigateDetailPusatInformasi = onNavigateDetailPusatInformasi
      )
    }
    composable(
      route = "pusat-informasi/{id}",
      arguments = listOf(navArgument("id") { type = NavType.StringType })
    ) { backStackEntry ->
      val id = backStackEntry.arguments?.getString("id") ?: ""
      if (id.isEmpty()) throw IllegalStateException("id must not be empty")
      DetailPusatInformasiScreen(
        modifier = modifier,
        id = id
      )
    }

    composable(
      route = Screen.Peserta.FormMonthlyReport.route,
    ) {
      val onNavigateDetailFormMonthlyReport = { id : String ->
        navController.navigateSingleTop("form-monthly-report/$id")
      }
      FormMonthlyReportScreen(
        modifier = modifier,
        onNavigateDetailFormMonthlyReport = onNavigateDetailFormMonthlyReport
      )
    }
    composable(
      route = "form-monthly-report/{id}",
      arguments = listOf(navArgument("id") { type = NavType.StringType })
    ) { backStackEntry ->
      val id = backStackEntry.arguments?.getString("id") ?: ""
      if (id.isEmpty()) throw IllegalStateException("id must not be empty")
      DetailFormMonthlyReportScreen(
        modifier = modifier,
        id = id
      )
    }

  }


}
