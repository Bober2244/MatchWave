package dev.bober.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,

    ) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = modifier.padding(8.dp),
            textStyle = MaterialTheme.typography.bodyLarge,
            label = { Text("Email") }
        )
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = modifier.padding(8.dp),
            textStyle = MaterialTheme.typography.bodyLarge,
            label = { Text("Password") }
        )
        Button(
            onClick = {
                //TODO:
            },
        ) {
            Text("Submit")
        }
        Spacer(
            modifier = modifier.height(50.dp)
        )
        ElevatedButton(
            onClick = {
                //TODO:
            },
        ) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "",
            )
            Text("Sign in with Google")
        }
        ElevatedButton(
            onClick = {
                //TODO:
            },
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "",
            )
            Text("Sign in with VK")
        }
        ElevatedButton(
            onClick = {
                //TODO:
            },
        ) {
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = "",
            )
            Text("Sign in with ")
        }
    }
}

@Composable
@Preview(showBackground = true, heightDp = 700, widthDp = 350)
fun AuthScreenPreview() {
    AuthScreen()
}