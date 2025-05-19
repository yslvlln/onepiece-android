package com.ycosilvallana.onepiece.domain.use_cases.read_onboarding

import com.ycosilvallana.onepiece.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class ReadOnboardingUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return userRepository.readOnboardingStatus()
    }
}