package com.example.socialapp.presentation.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun LoginScreen(
    modifier: Modifier = Modifier,
    onInteraction: (LoginInteraction) -> Unit,
    uiStateFlow: StateFlow<LoginUiState>,
    onNavigateSignUp: () -> Unit,
) {
    val uiState: LoginUiState = uiStateFlow.collectAsState().value

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(PurpleBlack)
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
        ) {
            SignToolbarComponent(
                toolbarTitle = stringResource(id = R.string.login_text),
            )
            Spacer(modifier = modifier.height(20.dp))
            SocialTextFieldComponent(
                text = uiState.userName,
                onValueChange = { onInteraction(LoginInteraction.OnUserNameChanged(it)) },
                label = stringResource(id = R.string.first_name)
            )
            SocialTextFieldComponent(
                text = uiState.userPassword,
                onValueChange = { onInteraction(LoginInteraction.OnUserPasswordChanged(it)) },
                label = stringResource(id = R.string.paswword)
            )
            AuthBtnComponent(
                btnText = stringResource(id = R.string.login_text),
                modifier = modifier.padding(top = 15.dp),
                onClick = { onInteraction(LoginInteraction.OnLoginButtonClickEvent) },
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.dont_have_account),
                    color = MaterialTheme.colorScheme.background,
                    fontSize = 14.sp
                )
                Text(
                    text = stringResource(id = R.string.register_text),
                    color = SocialBlue,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .clickable(onClick = onNavigateSignUp)
                        .padding(start = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen(
            onInteraction = {},
            uiStateFlow = MutableStateFlow(LoginUiState()),
            onNavigateSignUp = {},
        )
    }
}