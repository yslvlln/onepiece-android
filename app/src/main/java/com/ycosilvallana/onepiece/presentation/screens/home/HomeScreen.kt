package com.ycosilvallana.onepiece.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val allCharacters = homeViewModel.getAllCharacters.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClicked = {}
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        )
    }

}