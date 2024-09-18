package dev.bober.auth.presentation.authstart

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.bober.auth.R
import dev.bober.auth.presentation.core.EmailOutlinedTextField
import dev.bober.auth.presentation.core.PasswordOutlinedTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthScreenViewModel = koinViewModel()
) {

    var emailText by rememberSaveable { mutableStateOf("") }
    var passwordText by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(),
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

        Button(
            onClick = {
                //TODO: Надо еще доделать логику
                viewModel.login(emailText, passwordText)
            },
        ) {
            Text(stringResource(R.string.sign_in_button_text))
        }
        Text(
            text = stringResource(R.string.forgot_password_text),
            color = Color.Blue,
            modifier = Modifier
                .padding(vertical = 4.dp)
                .clickable {
                    //TODO:
                }
        )
        Spacer(
            modifier = Modifier.height(50.dp)
        )
        ButtonWithLink(
            R.drawable.ic_google,
            R.string.login_with_google,
            onClick = {
                //TODO:
            }
        )
        ButtonWithLink(
            R.drawable.ic_vk,
            R.string.login_with_vk,
            onClick = {
                //TODO:
            }
        )
        ButtonWithLink(
            R.drawable.visibility_24px,
            R.string.sign_up_button_text,
            onClick = {
                //TODO:
            }
        )
    }
}

@Composable
fun ButtonWithLink(
    @DrawableRes icon: Int,
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "",
            modifier = modifier.size(24.dp, 24.dp)
        )
        Spacer(modifier = modifier.padding(start = 4.dp))
        Text(stringResource(text))
    }
}

@Composable
@Preview(showBackground = true, heightDp = 700, widthDp = 350)
fun AuthScreenPreview() {
    AuthScreen()
}

@Composable
@Preview
fun ButtonWithLinkPreview() {
    ButtonWithLink(
        R.drawable.ic_google,
        R.string.login_with_google,
        {}
    )
}