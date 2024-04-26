package com.example.socialapp.presentation.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.socialapp.R
import com.example.socialapp.presentation.auth.components.AuthBtnComponent
import com.example.socialapp.presentation.auth.components.SignToolbarComponent
import com.example.socialapp.presentation.auth.components.SocialTextFieldComponent
import com.example.socialapp.presentation.theme.PurpleBlack
import com.example.socialapp.presentation.theme.SocialBlue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun RegisterScreen(
    onInteraction: (RegisterInteraction) -> Unit,
    modifier: Modifier = Modifier,
    uiStateFlow: StateFlow<RegisterUiState>,
    onNavigateToLogin: () -> Unit,
) {
    val uiState: RegisterUiState = uiStateFlow.collectAsState().value

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(PurpleBlack)
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier,
        ) {
            SignToolbarComponent(
                toolbarTitle = stringResource(id = R.string.register_text)
            )
            SocialTextFieldComponent(
                text = uiState.userFirstName,
                onValueChange = {
                    onInteraction(RegisterInteraction.OnUserFirstNameChanged(it))
                },
                label = stringResource(id = R.string.first_name)
            )

            SocialTextFieldComponent(
                text = uiState.userLastName,
                onValueChange = {
                    onInteraction(RegisterInteraction.OnUserLastNameChanged(it))
                },
                label = stringResource(id = R.string.last_name)
            )

            SocialTextFieldComponent(
                text = uiState.userEmail,
                onValueChange = {
                    onInteraction(RegisterInteraction.OnUserEmailChanged(it))
                },
                label = stringResource(id = R.string.email)
            )

            SocialTextFieldComponent(
                text = uiState.userPassword,
                onValueChange = {
                    onInteraction(RegisterInteraction.OnUserPasswordChanged(it))
                },
                label = stringResource(id = R.string.paswword)
            )

            AuthBtnComponent(
                btnText = stringResource(id = R.string.register_text),
                onClick = { onInteraction(RegisterInteraction.OnRegisterButtonClick) },
                modifier = Modifier.padding(top = 14.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.have_account),
                    color = MaterialTheme.colorScheme.background,
                    fontSize = 14.sp
                )
                Text(
                    text = stringResource(id = R.string.login_text),
                    color = SocialBlue,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .clickable(onClick = { onNavigateToLogin() })
                        .padding(start = 8.dp)
                )
            }
        }
    }
}


@Preview
@Composable
fun RegisterScreenPreview() {
    MaterialTheme {
        RegisterScreen(
            onInteraction = {},
            uiStateFlow = MutableStateFlow(RegisterUiState()),
            onNavigateToLogin = {  }
        )
    }
}