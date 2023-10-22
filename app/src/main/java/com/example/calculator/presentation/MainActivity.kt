package com.example.calculator.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.calculator.presentation.BMIResult.BMIResultScreen
import com.example.calculator.presentation.calculator.CalculatorScreen
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.utill.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    NavHost(navController = navController, startDestination = Screen.CalculatorScreen.route) {
                        composable(Screen.CalculatorScreen.route) {
                            CalculatorScreen(navController)
                        }
                        composable(
                            route = Screen.BMIResultScreen.route +
                                    "?bmiResult={bmiResult}&isManSelected={isManSelected}",
                            arguments = listOf(
                                navArgument(
                                    name = "bmiResult"
                                ) {
                                    type = NavType.FloatType
                                    defaultValue = 0.0
                                },
                                navArgument(
                                    name = "isManSelected"
                                ) {
                                    type = NavType.BoolType
                                    defaultValue = true
                                }
                            )
                        ) {
                            BMIResultScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
