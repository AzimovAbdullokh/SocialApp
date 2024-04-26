package com.example.socialapp.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.UserSharedPref
import com.example.domain.usecase.GetAllUsersUseCase
import com.example.socialapp.presentation.mappers.toUio
import com.example.socialapp.presentation.models.UsersListUiModel
import com.example.socialapp.presentation.screens.main.model.LoadedMainScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val userSharedPref: UserSharedPref,
    private val getAllUsersUseCase: GetAllUsersUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<MainScreenUiState> =
        MutableStateFlow(MainScreenUiState.Loading)
    val uiState: StateFlow<MainScreenUiState> get() = _uiState.asStateFlow()

    init {
        doLoadAllMainScreenData()
    }

    fun onInteraction(interaction: MainScreenInteraction) {
        when (interaction) {
            else -> {}
        }
    }

    private fun doLoadAllMainScreenData() {
        viewModelScope.launch {
            val currentUserName = userSharedPref.getCurrentUser().firstName
            val response = getAllUsersUseCase()

            if (response.isSuccess) {
                _uiState.tryEmit(MainScreenUiState.Loaded(LoadedMainScreenModel(userName = currentUserName,
                    allUsers = UsersListUiModel(result = response.getOrNull()?.result?.map { it.toUio() }
                        .orEmpty()))))
            }
        }
    }
}