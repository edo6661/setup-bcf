package com.example.slicingbcf.implementation.auth.registrasi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slicingbcf.di.IODispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrasiUmpanBalikViewModel @Inject constructor(
  @IODispatcher private val ioDispatcher : CoroutineDispatcher,
) : ViewModel() {

  private val _uiState = MutableStateFlow(RegistrasiUmpanBalikState())
  val uiState = _uiState.asStateFlow()

  fun onEvent(event : RegistrasiUmpanBalikEvent) {
    when (event) {
      is RegistrasiUmpanBalikEvent.ChangeEmote                     -> {
        _uiState.update { it.copy(currentEmote = event.emote) }
      }

      is RegistrasiUmpanBalikEvent.ChangePengalamanPendaftaranLead -> {
        _uiState.update { it.copy(pengalamanPendaftaranLead = event.pengalaman) }
      }

      is RegistrasiUmpanBalikEvent.ClearState                      -> {
        _uiState.update { RegistrasiUmpanBalikState() }
      }

      is RegistrasiUmpanBalikEvent.SendForm                        -> {
        submitForm()
      }
    }
  }

  private fun submitForm() {
    viewModelScope.launch(ioDispatcher) {
      try {
        _uiState.update { it.copy(isLoading = true) }
        delay(1000) // Simulasi network call
        _uiState.update { it.copy(isLoading = false, isSuccess = true) }
      } catch (e : Exception) {
        _uiState.update {
          it.copy(
            isLoading = false,
            isSuccess = false,
            error = e.message
          )
        }
      }
    }
  }
}


data class RegistrasiUmpanBalikState(
  val currentEmote : String = "",
  val pengalamanPendaftaranLead : String = "",
  val isLoading : Boolean = false,
  val isSuccess : Boolean = false,
  val error : String? = null
)

sealed class RegistrasiUmpanBalikEvent {
  data class ChangeEmote(val emote : String) : RegistrasiUmpanBalikEvent()
  data class ChangePengalamanPendaftaranLead(val pengalaman : String) : RegistrasiUmpanBalikEvent()
  object SendForm : RegistrasiUmpanBalikEvent()
  object ClearState : RegistrasiUmpanBalikEvent()
}
