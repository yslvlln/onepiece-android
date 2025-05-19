package com.ycosilvallana.onepiece.domain.use_cases.save_onboarding

import com.ycosilvallana.onepiece.data.repository.UserRepository

class SaveOnboardingUseCase(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(completed: Boolean) {
        userRepository.saveOnboardingStatus(completed = completed)
    }
}