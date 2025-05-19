package com.ycosilvallana.onepiece.domain.use_cases

import com.ycosilvallana.onepiece.domain.use_cases.read_onboarding.ReadOnboardingUseCase
import com.ycosilvallana.onepiece.domain.use_cases.save_onboarding.SaveOnboardingUseCase

data class UseCases(
    val saveOnboardingUseCases: SaveOnboardingUseCase,
    val readOnboardingUseCase: ReadOnboardingUseCase
)