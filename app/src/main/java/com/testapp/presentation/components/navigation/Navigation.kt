package com.testapp.presentation.components.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.testapp.presentation.home.MainViewModel

@Composable
fun MainAppGraph(
    navController: NavHostController = rememberNavController()
) {

    val mainViewModel: MainViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = Destinations.CategoriesScreen.route,
    ) {
        categoriesDestination(mainViewModel, navController)
        tableDestination(mainViewModel, navController)
        detailsDestination()
    }
}