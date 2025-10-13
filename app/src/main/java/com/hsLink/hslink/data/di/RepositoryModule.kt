package com.hsLink.hslink.data.di

import com.hsLink.hslink.data.repositoryimpl.DummyRepositoryImpl
import com.hsLink.hslink.domain.DummyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsDummyRepository(
        dummyRepositoryImpl: DummyRepositoryImpl
    ): DummyRepository

}