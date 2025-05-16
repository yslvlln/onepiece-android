package com.ycosilvallana.onepiece.domain.model

import androidx.annotation.DrawableRes
import com.ycosilvallana.onepiece.R

sealed class OnboardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    data object First : OnboardingPage(
        image = R.drawable.ic_explore,
        title = "Explore the Grand Line",
        description = "Browse through your favorite One Piece characters, from Straw Hats to Warlords and beyond."
    )
    data object Second : OnboardingPage(
        image = R.drawable.ic_statistics,
        title = "Uncover Epic Abilities",
        description = "Learn about each character’s power level, special abilities, and nature types in detail."
    )
    data object Third : OnboardingPage(
        image = R.drawable.ic_info,
        title = "Dive Into Their World",
        description = "Discover birthdays, family ties, and backstories that bring every character to life."
    )
}