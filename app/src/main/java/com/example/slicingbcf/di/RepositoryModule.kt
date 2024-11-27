package com.example.slicingbcf.di

import com.example.slicingbcf.data.repo.user.UserRepository
import com.example.slicingbcf.data.repo.user.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

  @Binds
  @Singleton
  abstract fun bindUserRepository(
    userRepositoryImpl : UserRepositoryImpl
  ) : UserRepository


}