package dev.bober.auth.presentation.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import dev.bober.auth.presentation.login.LoginScreen
import dev.bober.auth.presentation.registration.AddBirthdayScreen
import dev.bober.auth.presentation.registration.AddNameScreen
import dev.bober.auth.presentation.registration.RegistrationScreen
import dev.bober.auth.presentation.requestcode.RequestCodeScreen
import kotlinx.serialization.Serializable

//Route for nested graph
@Serializable
object AuthGraph

//Routes inside nested graph
@Serializable
object LoginRoute

@Serializable
object RegistrationRoute

@Serializable
object RequestCodeRoute

@Serializable
data class AddNameRoute(
    val email: String,
    val password: String,
)

@Serializable
data class AddBirthdayRoute(
    val email: String,
    val password: String,
    val name: String,
)

fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation<AuthGraph>(startDestination = LoginRoute) {
        composable<LoginRoute> {
            LoginScreen(
                onRegisterWithEmail = {
                    navController.navigate(
                        route = RegistrationRoute
                    )
                }
            )
        }
        composable<RegistrationRoute> {
            RegistrationScreen(
                onRegistrationClick = { email, password ->
                    navController.navigate(
                        route = AddNameRoute(
                            email = email,
                            password = password
                        )
                    )
                }
            )
        }
        composable<AddNameRoute> {
            val addNameRoute = it.toRoute<AddNameRoute>()
            AddNameScreen(
                onNextClick = { name ->
                    navController.navigate(
                        route = AddBirthdayRoute(
                            name = name,
                            email = addNameRoute.email,
                            password = addNameRoute.password
                        )
                    )
                },
            )
        }
        composable<RequestCodeRoute> {
            RequestCodeScreen()
        }
        composable<AddBirthdayRoute> {
            AddBirthdayScreen(
                onNextClick = { //TODO: Тут конец регистрации
                    navController.navigate(
                        route = RequestCodeRoute
                    )
                },
                datePickerState = remember { mutableStateOf(false) },
            )
        }
    }
}