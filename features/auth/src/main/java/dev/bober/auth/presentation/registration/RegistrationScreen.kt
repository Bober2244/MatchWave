package dev.bober.auth.presentation.registration

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.bober.auth.R
import dev.bober.auth.presentation.core.EmailOutlinedTextField
import dev.bober.auth.presentation.core.PasswordOutlinedTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    viewModel: RegistrationScreenViewModel = koinViewModel()
) {

    var emailText by rememberSaveable { mutableStateOf("") }
    var passwordText by rememberSaveable { mutableStateOf("") }
    var passwordRepeatText by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        EmailOutlinedTextField(
            email = emailText,
            onEmailChange = {
                emailText = it
            }
        )
        PasswordOutlinedTextField(
            password = passwordText,
            onPasswordChanged = {
                passwordText = it
            }
        )
        PasswordOutlinedTextField(
            password = passwordRepeatText,
            onPasswordChanged = {
                passwordRepeatText = it
            },
            labelText = stringResource(R.string.repeat_password_hint_text)
        )
        Button(
            onClick = {
                //TODO: Надо еще доделать логику
                viewModel.register(
                    email = emailText,
                    name = "Сергей",
                    password = passwordText,
                    birthday = "2004-08-31"
                )
                Log.i("RegistrationButtonClick", "Button clicked")
            }
        ) {
            Text(stringResource(R.string.sign_up_button_text))
        }
    }
}

@Preview(showBackground = true, heightDp = 700, widthDp = 350)
@Composable
private fun RegistrationScreenPreview() {
    RegistrationScreen()
}