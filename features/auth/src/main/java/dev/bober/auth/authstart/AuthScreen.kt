package dev.bober.auth.authstart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.bober.auth.ButtonWithLink
import dev.bober.auth.R

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    ) {
    var emailText by rememberSaveable { mutableStateOf("") }
    var passwordText by rememberSaveable { mutableStateOf("") }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        OutlinedTextField(
            value = emailText,
            onValueChange = { email ->
                emailText = email
            },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge,
            label = { Text(text = stringResource(R.string.email_hint_text)) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null,
                )
                          },
        )
        OutlinedTextField(
            value = passwordText,
            onValueChange = { password ->
                passwordText = password
            },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp).fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge,
            label = { Text(text = stringResource(R.string.password_hint_text)) },
            singleLine = true,
            leadingIcon = {
                Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
                )
                          },
        )
        Button(
            onClick = {
                //TODO:
            },
        ) {
            Text("Sign")
        }
        Text(
            text = stringResource(R.string.forgot_text),
            color = Color.Blue,
            modifier = Modifier.padding(vertical = 4.dp).clickable {
                //TODO:
            }
        )
        Spacer(
            modifier = Modifier.height(50.dp)
        )
        ButtonWithLink(
            R.drawable.google_icon,
            R.string.login_with_google,
            onClick = {
                //TODO:
            }
        )
        ButtonWithLink(
            R.drawable.vk_logo,
            R.string.login_with_vk,
            onClick = {
                //TODO:
            }
        )
    }
}

@Composable
@Preview(showBackground = true, heightDp = 700, widthDp = 350)
fun AuthScreenPreview() {
    AuthScreen()
}