package com.example.slicingbcf.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.slicingbcf.implementation.mentor.data_peserta.DataPesertaMentorScreen
import com.example.slicingbcf.implementation.mentor.feedback_peserta.FeedbackPesertaScreen
import com.example.slicingbcf.implementation.mentor.forum_diskusi.DetailForumDiskusiScreen
import com.example.slicingbcf.implementation.mentor.forum_diskusi.ForumDiskusiScreen
import com.example.slicingbcf.implementation.mentor.forum_diskusi.SearchForumDiskusiScreen
import com.example.slicingbcf.implementation.mentor.kelompok_mentoring_mentor.KelompokMentoringScreen
import com.example.slicingbcf.implementation.mentor.pengaturan_mentor.PengaturanScreen
import com.example.slicingbcf.implementation.mentor.pengumuman_mentor.DetailPengumumanMentorScreen
import com.example.slicingbcf.implementation.mentor.pengumuman_mentor.PengumumanMentorScreen
import com.example.slicingbcf.implementation.mentor.penilaian_peserta.DetailPenilaianPesertaScreen
import com.example.slicingbcf.implementation.mentor.penilaian_peserta.PenilaianPesertaScreen
import com.example.slicingbcf.implementation.mentor.pitchdeck.DetailPitchdeckScreen
import com.example.slicingbcf.implementation.mentor.pitchdeck.MoreDetailPitchdeckScreen
import com.example.slicingbcf.implementation.mentor.pitchdeck.PitchdeckScreen

fun NavGraphBuilder.mentorNavGraph(
  modifier : Modifier,
  navController : NavHostController
) {
  navigation(
    startDestination = Screen.Mentor.ForumDiskusi.route, route = "mentor"
  ) {
    composable(
      route = Screen.Mentor.PenilaianPeserta.route
    ) {

      val onNavigateDetailPenilaianPeserta = { id : String ->
        navController.navigate("penilaian-peserta-mentor/$id")
      }

      PenilaianPesertaScreen(
        modifier = modifier,
        onNavigateDetailPenilaianPeserta = onNavigateDetailPenilaianPeserta
      )
    }
    composable(
      route = "penilaian-peserta-mentor/{id}",
      arguments = listOf(navArgument("id") { type = NavType.StringType })
    ) {


      DetailPenilaianPesertaScreen(
        modifier = modifier,
        id = it.arguments?.getString("id") ?: "1"
      )
    }
    composable(
      route = Screen.Mentor.FeedbackPeserta.route
    ) {
      FeedbackPesertaScreen(
        modifier = modifier
      )
    }
    composable(
      route = Screen.Mentor.Pitchdeck.route
    ) {

      val onNavigateDetailPitchdeck = { id : String ->
        navController.navigateSingleTop("pitchdeck/$id")
      }

      PitchdeckScreen(
        modifier = modifier,
        onNavigateDetailPitchdeck = onNavigateDetailPitchdeck
      )
    }
    composable(
      route = "pitchdeck/{id}",
      arguments = listOf(navArgument("id") { type = NavType.StringType })
    ) {

      val onNavigateMoreDetailPitchdeck = { id : String ->
        navController.navigateSingleTop("pitchdeck/$id/more")
      }

      DetailPitchdeckScreen(
        modifier = modifier,
        onNavigateMoreDetailPitchdeck = onNavigateMoreDetailPitchdeck,
        id = it.arguments?.getString("id") ?: "1"
      )
    }

    composable(
      route = "pitchdeck/{id}/more",
      arguments = listOf(navArgument("id") { type = NavType.StringType })
    ) {
      val id = it.arguments?.getString("id") ?: "1"

      MoreDetailPitchdeckScreen(
        modifier = modifier,

        )
    }
    composable(
      route = Screen.Mentor.ForumDiskusi.route
    ) {
      val onNavigateDetailForumDiskusi = { id : String ->
        navController.navigateSingleTop("forum-diskusi/$id")
      }
      val onNavigateSearchForumDiskusi = {
        navController.navigateSingleTop(Screen.Mentor.SearchForumDiskusi.route)
      }
      ForumDiskusiScreen(
        modifier = modifier,
        onNavigateDetailForumDiskusi = onNavigateDetailForumDiskusi,
        onNavigateSearchForumDiskusi = onNavigateSearchForumDiskusi
      )
    }
    composable(
      route = Screen.Mentor.SearchForumDiskusi.route
    ) {

      val onNavigateDetailForumDiskusi = { id : String ->
        navController.navigateSingleTop("forum-diskusi/$id")
      }

      SearchForumDiskusiScreen(
        modifier = modifier,
        onNavigateDetailForumDiskusi = onNavigateDetailForumDiskusi
      )
    }
    composable(
      route = "forum-diskusi/{id}",
      arguments = listOf(navArgument("id") { type = NavType.StringType })
    ) {
      DetailForumDiskusiScreen(
        modifier = modifier,
        id = it.arguments?.getString("id") ?: "1"
      )
    }
    composable(
      route = Screen.Mentor.DataPeserta.route
    ) {
      DataPesertaMentorScreen(
        modifier = modifier,

        )
    }

    composable(
      route = Screen.Mentor.KelompokMentoring.route,
    ) {
      KelompokMentoringScreen(
        modifier = modifier,
      )
    }
    composable(
      route = Screen.Mentor.Pengaturan.route,
    ) {
      PengaturanScreen(
        modifier = modifier,
      )
    }

    composable(
      route = Screen.Mentor.Pengumuman.route
    ) {
      val onNavigateDetailPengumuman = { id : String ->
        navController.navigateSingleTop("pengumuman-mentor/$id")
      }
      PengumumanMentorScreen(
        modifier = modifier,
        onNavigateDetailPengumuman = onNavigateDetailPengumuman
      )
    }
    composable(
      route = "pengumuman-mentor/{id}",
      arguments = listOf(navArgument("id") { type = NavType.StringType })
    ) { backStackEntry ->
      val id = backStackEntry.arguments?.getString("id") ?: ""
      if (id.isEmpty()) throw IllegalStateException("id must not be empty")
      DetailPengumumanMentorScreen(
        modifier = modifier.padding(
        ), id = id
      )
    }
  }


}