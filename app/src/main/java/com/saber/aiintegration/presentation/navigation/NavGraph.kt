package com.saber.aiintegration.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.saber.aiintegration.presentation.screens.HomeScreen
import com.saber.aiintegration.presentation.screens.LandmarkClassifierScreen
import com.saber.aiintegration.presentation.screens.OnboardingScreen
import com.saber.aiintegration.presentation.screens.SettingsScreen

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
                onSettingCallBack = {
                    navController.navigate(Settings)
                },
                onCameraCallBack = {
                    navController.navigate(LandmarkClassifier)
                })
        }
        composable<Settings> {
            SettingsScreen {
                navController.navigate(Home)
            }
        }
        composable<LandmarkClassifier> {
            LandmarkClassifierScreen {
                navController.navigate(Home)
            }
        }
    }
}