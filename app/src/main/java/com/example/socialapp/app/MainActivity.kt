package com.example.socialapp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.socialapp.presentation.screens.main.MAIN_GRAPH_TAG
import com.example.socialapp.presentation.screens.utils.emptyString
import com.example.socialapp.presentation.theme.SocialAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialAppTheme {
                val viewModel: SocialMainAppViewModel = hiltViewModel()
                SocialMainApp(
                    viewModel.userDestinationFlow,
                    if (viewModel.isUserSigned) MAIN_GRAPH_TAG
                    else emptyString()
                )
            }
        }
    }
}
