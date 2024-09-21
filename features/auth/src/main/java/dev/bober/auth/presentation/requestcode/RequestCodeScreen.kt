package dev.bober.auth.presentation.requestcode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
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
import org.koin.androidx.compose.koinViewModel

@Composable
fun RequestCodeScreen(
    modifier: Modifier = Modifier,
    viewModel : RequestCodeViewModel = koinViewModel()
) {
    var code by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        OutlinedTextField(
            value = code,
            onValueChange = {
                code = it
                viewModel.checkCode()
            },
            label = { Text(stringResource(R.string.code_text_input_hint)) },
        )
    }
}