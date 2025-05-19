package com.ycosilvallana.onepiece.presentation.screens.splash

import android.content.res.Configuration
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ycosilvallana.onepiece.R
import com.ycosilvallana.onepiece.navigation.Screen

@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val onboardingCompleted by splashViewModel.onboardingCompleted.collectAsState()
    val degrees = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navController.popBackStack()
        if (onboardingCompleted) {
            navController.navigate(Screen.Home.route)
        } else {
            navController.navigate(Screen.Welcome.route)
        }
    }

    Splash(degrees = degrees.value)
}

@Composable
fun Splash(degrees: Float) {
    val backgroundColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    Box(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(120.dp)
                .rotate(degrees = degrees),
            painter = painterResource(id = R.drawable.ic_skull),
            contentDescription = stringResource(R.string.app_logo)
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    Splash(degrees = 0f)
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun SplashScreenDarkPreview() {
    Splash(degrees = 0f)
}