package com.saber.aiintegration.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.saber.aiintegration.presentation.screens.HomeScreen
import com.saber.aiintegration.presentation.screens.LandmarkClassifierScreen
import com.saber.aiintegration.presentation.screens.OnboardingScreen

@Composable
fun ApplicationNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Onboarding) {
        composable<Onboarding> {
            OnboardingScreen {
                navController.navigate(Home)
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