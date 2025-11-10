package com.hsLink.hslink.data.di

import com.hsLink.hslink.data.remote.datasource.CommunityPostDataSource
import com.hsLink.hslink.data.remote.datasource.PostDataSource
import com.hsLink.hslink.data.remote.datasourceimpl.CommunityPostDataSourceImpl
import com.hsLink.hslink.data.remote.datasourceimpl.PostDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    abstract fun bindePostRemoteDataSource(
        postDataSourceImpl: PostDataSourceImpl,
    ): PostDataSource

    @Binds
    abstract fun bindsPostLocalDataSource(
        communityPostDataSourceImpl: CommunityPostDataSourceImpl,
    ): CommunityPostDataSource
}