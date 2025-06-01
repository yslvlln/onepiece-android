package com.ycosilvallana.onepiece.domain.use_cases.save_onboarding

import com.ycosilvallana.onepiece.data.repository.Repository

class SaveOnboardingUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnboardingStatus(completed = completed)
    }
}