package dev.bober.auth.presentation.registration

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.bober.auth.R
import dev.bober.auth.presentation.core.EmailOutlinedTextField
import dev.bober.auth.presentation.core.PasswordOutlinedTextField
import org.koin.androidx.compose.koinViewModel
import java.util.Date
import java.util.Locale

@Composable
fun RegistrationScreen(
    onRegistrationClick: (email: String, password: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel = koinViewModel()
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
        ElevatedButton(
            onClick = {
                viewModel.email = emailText
                viewModel.password = passwordText
                //TODO: Доделать логику
                onRegistrationClick(
                    emailText,
                    passwordText,
                )
            }
        ) {
            Text(stringResource(R.string.sign_up_button_text))
        }
        Spacer(modifier = Modifier.height(215.dp))
    }
}

@Composable
fun AddNameScreen(
    onNextClick: (name: String) -> Unit,
    modifier: Modifier = Modifier,
    nameState: MutableState<String> = rememberSaveable { mutableStateOf("") },
    viewModel: RegistrationViewModel = koinViewModel(),
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = nameState.value,
            onValueChange = {
                nameState.value = it
            },
            label = {
                Text(stringResource(R.string.name_hint_text))
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        ElevatedButton(
            onClick = {
                viewModel.name = nameState.value
                onNextClick(
                    viewModel.email,
                )
            },
            modifier = Modifier.padding(top = 16.dp),
        ) {
            Text(stringResource(R.string.next_button_text))
        }
        Spacer(modifier = Modifier.height(200.dp))
    }
}

@Composable
fun AddBirthdayScreen(
    onNextClick: (birthday : String) -> Unit,
    datePickerState: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    viewModel: RegistrationViewModel = koinViewModel(),
) {
    var birthdayText by rememberSaveable { mutableStateOf("") }

    if (datePickerState.value) {
        SelectBirthday(
            onDateSelected = { millis ->
                millis?.let {
                    birthdayText = converter(it)
                }
            },
            onDismiss = {
                datePickerState.value = false
            },
        )
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = birthdayText,
            onValueChange = {},
            label = { Text(stringResource(R.string.birthday_hint_text)) },
            singleLine = true,
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable {
                    datePickerState.value = true
                },
            //TODO(При enabled = true область нажатия очень маленькая)
            enabled = false,
        )
        ElevatedButton(
            onClick = {
                viewModel.birthday = birthdayText

                viewModel.register(
                    email = viewModel.email,
                    password = viewModel.password,
                    name = viewModel.name,
                    birthday = birthdayText
                )
                onNextClick(
                    birthdayText
                )
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(stringResource(R.string.next_button_text))
        }
        Spacer(modifier = Modifier.height(200.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectBirthday(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateSelected(datePickerState.selectedDateMillis)
                    onDismiss()
                }
            ) { Text(stringResource(R.string.select_date_button_text)) }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel_date_button_text))
            }
        },
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        DatePicker(
            state = datePickerState,
            modifier = modifier,
        )
    }
}

fun converter(millis: Long): String {
    return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date(millis))
}

@Preview(showBackground = true, heightDp = 700, widthDp = 400)
@Composable
private fun DatePickerPreview() {
    SelectBirthday(
        onDateSelected = {},
        onDismiss = {}
    )
}