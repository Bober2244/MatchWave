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
import dev.bober.auth.presentation.registration.AddBirthday
import dev.bober.auth.presentation.registration.AddName
import dev.bober.auth.presentation.registration.RegistrationScreen
import dev.bober.auth.presentation.registration.navigation.AddBirthday
import dev.bober.auth.presentation.registration.navigation.AddName
import dev.bober.auth.presentation.registration.navigation.RegistrationScreen
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
                        startDestination = RegistrationScreen
                    ) {
                        composable<RegistrationScreen> {
                            RegistrationScreen(
                                modifier = Modifier.padding(innerPadding),
                                onRegistrationClick = { navController.navigate(route = AddName) }
                            )
                        }
                        composable<AddName> {
                            AddName(
                                onNextClick = { navController.navigate(route = AddBirthday) },
                                modifier = Modifier.padding(innerPadding),
                            )
                        }
                        composable<AddBirthday> {
                            AddBirthday(
                                onNextClick = { navController.navigate(route = AddBirthday) },
                                modifier = Modifier.padding(innerPadding),
                                datePickerState = remember { mutableStateOf(false) },
                            )
                        }
                    }
                }
            }
        }
    }
}