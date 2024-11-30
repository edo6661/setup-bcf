package com.example.slicingbcf.implementation.mentor.forum_diskusi

import com.example.slicingbcf.ui.shared.pusat_informasi.DataPusatInformasi
import com.example.slicingbcf.ui.shared.pusat_informasi.mockDataPusatInformasi

data class ForumDiskusiState(
  val pertanyaan : String = "",
  val isLoading : Boolean = false,
  val dataList : List<DataPusatInformasi> = mockDataPusatInformasi,
)

sealed class ForumDiskusiEvent {
  data class PertanyaanChanged(val pertanyaan : String) : ForumDiskusiEvent()
  data class AddPertanyaan(val newPertanyaan : DataPusatInformasi) : ForumDiskusiEvent()
  object ClearState : ForumDiskusiEvent()
}
