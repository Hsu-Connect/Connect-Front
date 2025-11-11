package com.hsLink.hslink.data.di

import com.hsLink.hslink.data.repositoryimpl.AuthRepositoryImpl
import com.hsLink.hslink.data.repositoryimpl.CommunityRepositoryImpl
import com.hsLink.hslink.data.repositoryimpl.DummyRepositoryImpl
import com.hsLink.hslink.data.repositoryimpl.home.PostRepositoryImpl
import com.hsLink.hslink.data.repositoryimpl.search.SearchRepositoryImpl
import com.hsLink.hslink.domain.DummyRepository
import com.hsLink.hslink.domain.repository.AuthRepository
import com.hsLink.hslink.domain.repository.community.CommunityRepository
import com.hsLink.hslink.domain.repository.home.PostRepository
import com.hsLink.hslink.domain.repository.search.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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

    @Binds
    @Singleton
    fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    fun bindsCommunityPostRepository(
        communityPostRepositoryImpl: CommunityRepositoryImpl,
    ): CommunityRepository

    @Binds
    fun bindsSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl,
    ): SearchRepository
}
