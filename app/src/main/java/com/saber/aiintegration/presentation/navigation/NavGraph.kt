package com.saber.aiintegration.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.saber.aiintegration.data.sharedpreference.OnboardingPreference
import com.saber.aiintegration.presentation.screens.HomeScreen
import com.saber.aiintegration.presentation.screens.LandmarkClassifierScreen
import com.saber.aiintegration.presentation.screens.OnboardingScreen

@Composable
fun ApplicationNavigation(navController: NavHostController, context: Context) {
    val startDestination = when(OnboardingPreference.isOnboardingComplete(context)) {
        true -> Home
        false -> Onboarding
    }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable<Onboarding> {
            OnboardingScreen {
                navController.navigate(Home)
                OnboardingPreference.setOnboardingComplete(context)
            }
        }
        composable<Home> {
            HomeScreen(
                onCameraCallBack = {
                    navController.navigate(LandmarkClassifier)
                })
        }
        composable<LandmarkClassifier> {
            LandmarkClassifierScreen {
                navController.navigate(Home)
            }
        }
    }
}