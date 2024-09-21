package dev.bober.matchwave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.bober.auth.presentation.navigation.AuthGraph
import dev.bober.auth.presentation.navigation.LoginScreen
import dev.bober.auth.presentation.navigation.authGraph
import dev.bober.matchwave.ui.theme.MatchWaveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatchWaveTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        startDestination = AuthGraph,
                        navController = navController,
                    ) {
                        authGraph(navController = navController)
                    }
                }
            }
        }
    }
}