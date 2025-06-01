package com.ycosilvallana.onepiece.domain.use_cases.read_onboarding

import com.ycosilvallana.onepiece.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnboardingUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readOnboardingStatus()
    }
}