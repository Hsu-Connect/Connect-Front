package com.hsLink.hslink.data.di

import com.hsLink.hslink.data.repositoryimpl.DummyRepositoryImpl
import com.hsLink.hslink.data.repositoryimpl.PostRepositoryImpl
import com.hsLink.hslink.domain.DummyRepository
import com.hsLink.hslink.domain.repository.PostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsDummyRepository(
        dummyRepositoryImpl: DummyRepositoryImpl,
    ): DummyRepository

    @Binds
    fun bindsPostRepository(
        postRepositoryImpl: PostRepositoryImpl,
    ): PostRepository

}