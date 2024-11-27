package com.example.slicingbcf.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class JangkauanPenerimaManfaat(
  val kota : String,
  val jumlah : Int
)