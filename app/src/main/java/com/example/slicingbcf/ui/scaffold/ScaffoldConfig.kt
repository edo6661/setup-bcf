package com.example.slicingbcf.ui.scaffold

import android.util.Log
import com.example.slicingbcf.ui.navigation.Screen

data class ScaffoldConfig(
  val showMainNav : Boolean = true,
  val showBackNav : Boolean = false,
)

fun scaffoldConfig(currentRoute : String?)
  : ScaffoldConfig {
  return when (currentRoute) {
    Screen.Auth.Login.route          -> ScaffoldConfig(
      showMainNav = false,
    )

    Screen.Auth.ForgotPassword.route -> ScaffoldConfig(
      showMainNav = false,
    )


    "pengumuman-peserta/{id}"        -> ScaffoldConfig(
      showMainNav = false,
      showBackNav = true,
    )

    "pengumuman-mentor/{id}"         -> ScaffoldConfig(
      showMainNav = false,
      showBackNav = true,
    )

    "worksheet-peserta/{id}"         -> {
      Log.d("scaffoldConfig", "test")
      ScaffoldConfig(
        showMainNav = false,
        showBackNav = true,
      )
    }

    "penilaian-peserta/{id}"         -> {
      ScaffoldConfig(
        showMainNav = false,
        showBackNav = true,
      )
    }

    "data-peserta-mentor/{id}"       -> {
      ScaffoldConfig(
        showMainNav = false,
        showBackNav = true,
      )
    }

    "penilaian-peserta-mentor/{id}"  -> {
      ScaffoldConfig(
        showMainNav = false,
        showBackNav = true,
      )
    }

    "pitchdeck/{id}"                 -> {
      ScaffoldConfig(
        showMainNav = false,
        showBackNav = true,
      )
    }

    "pitchdeck/{id}/more"            -> {
      ScaffoldConfig(
        showMainNav = false,
        showBackNav = true,
      )
    }

    "pusat-informasi/{id}"           -> {
      ScaffoldConfig(
        showMainNav = false,
        showBackNav = true,
      )
    }

    "forum-diskusi/{id}"             -> {
      ScaffoldConfig(
        showMainNav = false,
        showBackNav = true,
      )
    }

    "form-monthly-report/{id}"       -> {
      ScaffoldConfig(
        showMainNav = false,
        showBackNav = true,
      )
    }

    else                             -> ScaffoldConfig()

  }
}