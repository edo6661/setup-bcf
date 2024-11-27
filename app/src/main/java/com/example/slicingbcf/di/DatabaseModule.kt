package com.example.slicingbcf.di

import android.content.Context
import androidx.room.Room
import com.example.slicingbcf.data.UserDatabase
import com.example.slicingbcf.data.local.dao.user.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

  @Provides
  @Singleton
  fun provideUserDao(database : UserDatabase) : UserDao = database.userDao

  @Provides
  @Singleton
  fun provideDatabase(
    @ApplicationContext context : Context
  ) : UserDatabase = Room.databaseBuilder(
    context,
    UserDatabase::class.java,
    UserDatabase.Constants.DATABASE_NAME
  ).build()
}