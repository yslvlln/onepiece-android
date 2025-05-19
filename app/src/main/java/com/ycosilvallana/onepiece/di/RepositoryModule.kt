package com.ycosilvallana.onepiece.di

import android.content.Context
import com.ycosilvallana.onepiece.data.repository.DataStoreOperationsImpl
import com.ycosilvallana.onepiece.data.repository.UserRepository
import com.ycosilvallana.onepiece.domain.repository.DataStoreOperations
import com.ycosilvallana.onepiece.domain.use_cases.UseCases
import com.ycosilvallana.onepiece.domain.use_cases.read_onboarding.ReadOnboardingUseCase
import com.ycosilvallana.onepiece.domain.use_cases.save_onboarding.SaveOnboardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(userRepository: UserRepository): UseCases {
        return UseCases(
            saveOnboardingUseCases = SaveOnboardingUseCase(userRepository = userRepository),
            readOnboardingUseCase = ReadOnboardingUseCase(userRepository = userRepository)
        )
    }

}