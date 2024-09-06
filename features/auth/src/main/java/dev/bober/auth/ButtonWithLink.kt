package dev.bober.auth

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ButtonWithLink(
    @DrawableRes icon : Int,
    @StringRes text : Int,
    onClick : () -> Unit,
    modifier : Modifier = Modifier,
) {
    ElevatedButton(
        onClick = onClick,
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

@Preview
@Composable
fun ButtonWithLinkPreview() {
    ButtonWithLink(
        R.drawable.google_icon,
        R.string.login_with_google,
        onClick = {},
    )
}