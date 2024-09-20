package dev.bober.matchwave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.bober.auth.presentation.registration.AddBirthdayScreen
import dev.bober.auth.presentation.registration.AddNameScreen
import dev.bober.auth.presentation.registration.RegistrationScreen
import dev.bober.auth.presentation.navigation.AddBirthdayScreen
import dev.bober.auth.presentation.navigation.AddNameScreen
import dev.bober.auth.presentation.navigation.RegistrationScreen
import dev.bober.matchwave.ui.theme.MatchWaveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            MatchWaveTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = RegistrationScreen,
                        modifier = Modifier.padding(innerPadding),
                    ) {
                        composable<RegistrationScreen> {
                            RegistrationScreen(
                                onRegistrationClick = { email, password ->
                                    navController.navigate(
                                        route = AddNameScreen(
                                            email = email,
                                            password = password
                                        )
                                    )
                                }
                            )
                        }
                        composable<AddNameScreen> {
                            AddNameScreen(
                                onNextClick = { email, password, name ->
                                    navController.navigate(
                                        route = AddBirthdayScreen(
                                            name = name,
                                            email = email,
                                            password = password
                                        )
                                    )
                                },
                            )
                        }
                        composable<AddBirthdayScreen> {
                            AddBirthdayScreen(
                                onNextClick = { email, password, name, birthday ->
                                    navController.navigate(
                                        //TODO: Тут другой route
                                        route = AddBirthdayScreen(
                                            email = email,
                                            password = password,
                                            name = name,
                                        )
                                    )
                                },
                                datePickerState = remember { mutableStateOf(false) },
                            )
                        }
                    }
                }
            }
        }
    }
}