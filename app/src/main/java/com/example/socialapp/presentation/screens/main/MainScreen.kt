package com.example.socialapp.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.socialapp.presentation.screens.main.model.LoadedMainScreenModel
import com.example.socialapp.presentation.screens.main.ui.utils.AllUsersBlock
import com.example.socialapp.presentation.screens.main.ui.utils.MainScreenToolbar
import com.example.socialapp.presentation.theme.PurpleBlack
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    uiStateFlow: StateFlow<MainScreenUiState>,
) {
    when (val uiState = uiStateFlow.collectAsState().value) {
        is MainScreenUiState.Error -> Unit
        is MainScreenUiState.Loaded -> LoadedMainScreen(loadedState = uiState)
        is MainScreenUiState.Loading -> {
            Box(
                modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun LoadedMainScreen(
    modifier: Modifier = Modifier,
    loadedState: MainScreenUiState.Loaded,
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn {
            item {
                MainScreenToolbar(userName = loadedState.loadedMainScreen.userName)
            }

            item {
                AllUsersBlock(
                    allUsers = loadedState.loadedMainScreen.allUsers.result
                )
            }
        }
    }
}

@Preview
@Composable
fun LoadedMainScreenPreview() {
    MaterialTheme {
        LoadedMainScreen(
            modifier = Modifier.background(PurpleBlack),
            loadedState = MainScreenUiState.Loaded(LoadedMainScreenModel.Preview)
        )
    }
}