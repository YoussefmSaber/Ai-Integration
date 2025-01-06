package com.saber.aiintegration.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun ApplicationNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Onboarding) {
        composable<Onboarding> {

        }
        composable<Home> {

        }
        composable<Settings> {

        }
        composable<LandmarkClassifier> {

        }
    }
}