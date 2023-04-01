package com.testapp

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.testapp.presentation.components.navigation.Destinations
import com.testapp.presentation.components.navigation.MainAppGraph
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MainAppGraph(navController = navController)
        }
    }

    @Test
    fun appNavHost_verifyStartDestination() {
        composeTestRule.onNodeWithTag("test_tag_home_screen").assertIsDisplayed()
    }

    @Test
    fun appNavHost_verifyGoToTableScreenButton() {
        composeTestRule.onNodeWithTag("test_tag_go_to_table").performClick()
        val route = navController.currentDestination?.route
        Assert.assertEquals(route, Destinations.TableScreen.route)
    }


    @Test
    fun appNavHost_verifyGoToDetailsScreenButton() {
        composeTestRule.onNodeWithTag("test_tag_go_to_details").performClick()
        val route = navController.currentDestination?.route
        Assert.assertEquals(route, Destinations.TableScreen.route)
    }


}