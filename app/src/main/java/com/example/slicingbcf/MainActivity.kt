package com.example.slicingbcf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.slicingbcf.data.local.viewmodel.UserViewModel
import com.example.slicingbcf.ui.navigation.NavGraph
import com.example.slicingbcf.ui.navigation.Screen
import com.example.slicingbcf.ui.navigation.navigateAndClearStackButHome
import com.example.slicingbcf.ui.scaffold.MainScaffold
import com.example.slicingbcf.ui.scaffold.scaffoldConfig
import com.example.slicingbcf.ui.theme.SlicingBcfTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


  private val userViewModel : UserViewModel by viewModels()


  override fun onCreate(savedInstanceState : Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
//    insertDummyData()


    setContent {
      val user by userViewModel.currentUser.collectAsState()


      val navController = rememberNavController()
      val currentBackStackEntry = navController.currentBackStackEntryAsState()
      val currentRoute = currentBackStackEntry.value?.destination?.route
      fun isActiveRoute(route : String) : Boolean {
        return currentRoute == route
      }
      SlicingBcfTheme {

        MainScaffold(
          config = scaffoldConfig(currentRoute),
          isActiveRoute = ::isActiveRoute,
          user = user,
          logout = {
            userViewModel.viewModelScope.launch {
              userViewModel.clearUserSession()
            }
            navController.navigateAndClearStackButHome(Screen.Auth.Login.route)

          },
          navController = navController,

          ) { paddingValues ->
          NavGraph(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
          )
        }
      }
    }
  }


}
