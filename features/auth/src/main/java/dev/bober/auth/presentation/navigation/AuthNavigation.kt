package dev.bober.auth.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import dev.bober.auth.presentation.login.LoginScreen
import dev.bober.auth.presentation.registration.AddBirthdayScreen
import dev.bober.auth.presentation.registration.AddNameScreen
import dev.bober.auth.presentation.registration.RegistrationScreen
import kotlinx.serialization.Serializable

//Route for nested graph
@Serializable
object AuthGraph

//Routes inside nested graph
@Serializable
object LoginScreen

@Serializable
object RegistrationScreen

@Serializable
data class AddNameScreen(
    val email: String,
    val password: String,
)

@Serializable
data class AddBirthdayScreen(
    val email: String,
    val password: String,
    val name: String,
)

fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation<AuthGraph>(startDestination = LoginScreen){
        composable<LoginScreen> {
            LoginScreen(
                onRegisterWithEmail = {
                    navController.navigate(
                        route = RegistrationScreen
                    )
                }
            )
        }
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
                        //TODO: Тут конец регистрации и совсем другой route
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