package com.testapp.presentation.components.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.testapp.presentation.home.CategoriesScreen
import com.testapp.presentation.home.DetailsScreen
import com.testapp.presentation.home.MainViewModel
import com.testapp.presentation.home.TableScreen

sealed class Destinations(
    val route: String
) {
    object CategoriesScreen : Destinations("categories")
    object DetailsScreen : Destinations("details")
    object TableScreen : Destinations("table")
}

// Adds home screen to `this` NavGraphBuilder
fun NavGraphBuilder.categoriesDestination(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable(Destinations.CategoriesScreen.route) {
        // The ViewModel as a screen level state holder produces the screen
        // UI state and handles business logic for the screen

        val uiState = mainViewModel.mainScreenState
            .collectAsState()
            .value

        LaunchedEffect(true) {
            mainViewModel.getCategories()
        }
        CategoriesScreen(
            uiState = uiState,
            onCategorySelected = {
                mainViewModel.setCategory(it)
            },
            onSubCategorySelected = {
                mainViewModel.setSubCategory(it)
            },
            onOptionSelected = { category, isOther, option ->
                mainViewModel.onOptionSelected(category, isOther, option)
            },
            onSubmit = {
                navController.navigate(Destinations.TableScreen.route)
            },
        )
    }
}

// Adds home screen to `this` NavGraphBuilder
fun NavGraphBuilder.detailsDestination() {
    composable(Destinations.DetailsScreen.route) {
        DetailsScreen()
    }
}

// Adds table screen to `this` NavGraphBuilder
fun NavGraphBuilder.tableDestination(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    composable(Destinations.TableScreen.route) {
        // The ViewModel as a screen level state holder produces the screen
        // UI state and handles business logic for the screen
        TableScreen(mainViewModel.getTableData()) {
            navController.navigate(Destinations.DetailsScreen.route)
        }
    }
}

