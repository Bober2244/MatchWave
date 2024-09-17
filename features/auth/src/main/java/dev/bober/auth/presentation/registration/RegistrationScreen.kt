package dev.bober.auth.presentation.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.bober.auth.presentation.core.EmailOutlinedTextField

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier
) {

    var emailText by rememberSaveable { mutableStateOf("") }
    var passwordText by rememberSaveable { mutableStateOf("") }
    var passwordRepeatText by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        EmailOutlinedTextField(
            email = emailText,
        )
    }
}

@Preview(showBackground = true, heightDp = 700, widthDp = 350)
@Composable
private fun RegistrationScreenPreview() {

}