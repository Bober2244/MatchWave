package dev.bober.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,

    ) {
    var text by rememberSaveable { mutableStateOf("") }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { value ->
                text = value
            },
            modifier = modifier.padding(8.dp),
            textStyle = MaterialTheme.typography.bodyLarge,
            label = { Text("Email") },
            singleLine = true
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = modifier.padding(8.dp),
            textStyle = MaterialTheme.typography.bodyLarge,
            label = { Text("Password") },
            singleLine = true
        )
        Button(
            onClick = {

            },
        ) {
            Text("Sign")
        }
        Text(
            text = "Forgot the password?",
            color = Color.Blue,
            modifier = modifier.clickable {

            }
        )
        Spacer(
            modifier = modifier.height(50.dp)
        )
        ButtonWithLink(
            R.drawable.google_icon,
            R.string.login_with_google,
            onClick = {}
        )
        ButtonWithLink(
            R.drawable.vk_logo,
            R.string.login_with_vk,
            onClick = {}
        )
    }
}

@Composable
@Preview(showBackground = true, heightDp = 700, widthDp = 350)
fun AuthScreenPreview() {
    AuthScreen()
}