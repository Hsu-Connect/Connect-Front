package com.hsLink.hslink.data.di

import com.hsLink.hslink.data.remote.datasource.CommunityPostDataSource
import com.hsLink.hslink.data.remote.datasource.OnboardingDataSource
import com.hsLink.hslink.data.remote.datasource.PostDataSource
import com.hsLink.hslink.data.remote.datasource.SearchDataSource
import com.hsLink.hslink.data.remote.datasourceimpl.CommunityPostDataSourceImpl
import com.hsLink.hslink.data.remote.datasourceimpl.OnboardingDataSourceImpl
import com.hsLink.hslink.data.remote.datasourceimpl.PostDataSourceImpl
import com.hsLink.hslink.data.remote.datasourceimpl.SearchDataSourceImpl
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

    @Binds
    abstract fun bindsSearchDataSource(
        searchDataSourceImpl: SearchDataSourceImpl,
    ): SearchDataSource


    @Binds
    abstract fun bindsOnboardingDataSource(
        onboardingDataSourceImpl: OnboardingDataSourceImpl
    ): OnboardingDataSource

}