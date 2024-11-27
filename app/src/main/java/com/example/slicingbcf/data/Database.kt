package com.example.slicingbcf.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.slicingbcf.data.local.dao.user.UserDao
import com.example.slicingbcf.data.local.helper.Converters
import com.example.slicingbcf.data.local.model.User


@TypeConverters(
  value = [
    Converters::class
  ]
)
@Database(
  entities = [
    User::class
  ],
  version = 2,
  exportSchema = false
)

abstract class UserDatabase : RoomDatabase() {

  object Constants {

    const val DATABASE_NAME = "bcf_db"
  }

  abstract val userDao : UserDao
}