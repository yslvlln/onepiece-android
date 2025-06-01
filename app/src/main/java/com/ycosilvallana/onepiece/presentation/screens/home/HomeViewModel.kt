package com.ycosilvallana.onepiece.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.ycosilvallana.onepiece.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {

    val getAllCharacters = useCases.getAllCharactersUseCase()
}