package dev.bober.auth.presentation.navigation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import dev.bober.auth.presentation.login.LoginScreen
import dev.bober.auth.presentation.registration.AddBirthdayScreen
import dev.bober.auth.presentation.registration.AddNameScreen
import dev.bober.auth.presentation.registration.RegistrationScreen
import dev.bober.auth.presentation.requestcode.RequestCodeScreen
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.nullable

//Route for nested graph
@Serializable
object AuthGraph

//Routes inside nested graph
@Serializable
object LoginScreen

@Serializable
object RegistrationScreen

@Serializable
object RequestCodeScreen

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
        composable<AddNameScreen> { entry ->
            val addNameScreen = entry.toRoute<AddNameScreen>()
            AddNameScreen(
                onNextClick = { name ->
                    navController.navigate(
                        route = AddBirthdayScreen(
                            name = name,
                            email = addNameScreen.email,
                            password = addNameScreen.password
                        )
                    )
                },
            )
        }
        composable<RequestCodeScreen> {
            RequestCodeScreen()
        }
        composable<AddBirthdayScreen> { entry ->
            AddBirthdayScreen(
                onNextClick = { birthday -> //TODO: Тут конец регистрации, возможно убрать нужно birthday
                    navController.navigate(
                        route = RequestCodeScreen
                    )
                },
                datePickerState = remember { mutableStateOf(false) },
            )
        }
    }
}