package com.example.socialapp.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.socialapp.R
import com.example.socialapp.presentation.models.CurrentUserUiModel
import com.example.socialapp.presentation.theme.PurpleBlack
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    uiStateFlow: StateFlow<ProfileUiState>,
    onInteraction: (ProfileInteraction) -> Unit,
) {
    when (val uiState = uiStateFlow.collectAsState().value) {
        is ProfileUiState.Error -> {}
        is ProfileUiState.Loaded -> LoadedProfileScreen(
            data = uiState,
            modifier = modifier,
            onInteraction = onInteraction
        )

        is ProfileUiState.Loading -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoadedProfileScreen(
    data: ProfileUiState.Loaded,
    modifier: Modifier = Modifier,
    onInteraction: (ProfileInteraction) -> Unit,
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    val userBio = data.profile.userBio.ifEmpty { "The user has no biography" }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PurpleBlack)
    ) {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = PurpleBlack
            ),
            title = {
                Text(
                    text = "Profile",
                    fontWeight = FontWeight.Medium,
                    fontSize = 32.sp,
                    color = Color.White,
                )
            },
            actions = {
                IconButton(
                    modifier = Modifier
                        .size(42.dp)
                        .padding(end = 12.dp),
                    onClick = { showBottomSheet = !showBottomSheet },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AsyncImage(
                modifier = modifier
                    .size(140.dp)
                    .clip(CircleShape),
                model = data.profile.userAvatar.url,
                contentDescription = null,
            )
            Text(
                text = data.profile.firstName,
                modifier = Modifier.padding(top = 16.dp),
                color = Color.White,
                fontSize = 24.sp
            )
            Text(
                text = data.profile.lastName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .padding(horizontal = 24.dp),
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            )
            Text(
                text = userBio,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 24.dp),
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "2,467",
                        fontSize = 17.sp,
                        color = Color.White,
                    )

                    Text(
                        text = "Flowers",
                        color = Color.White,
                    )
                }
                Column {
                    Text(
                        text = "2,467",
                        fontSize = 17.sp,
                        color = Color.White,
                    )

                    Text(
                        text = "Following",
                        color = Color.White,
                    )
                }
                OutlinedButton(
                    onClick = { },
                    modifier = modifier,
                ) {
                    Text(
                        text = "Edit Profile",
                        color = Color.White,
                        fontSize = 14.sp,
                    )
                }
            }
        }
    }

    if (showBottomSheet) {
        ProfileBottomSheetDialog(
            bottomSheetVisibleChangeState = { showBottomSheet = false },
            onInteraction = onInteraction
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileBottomSheetDialog(
    modifier: Modifier = Modifier,
    bottomSheetVisibleChangeState: () -> Unit,
    onInteraction: (ProfileInteraction) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        modifier = modifier.height(120.dp),
        onDismissRequest = bottomSheetVisibleChangeState,
        sheetState = sheetState,
        containerColor = PurpleBlack,
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .clickable {
                    onInteraction(ProfileInteraction.OnLogoutFromAccount)
                    scope
                        .launch { sheetState.hide() }
                        .invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                bottomSheetVisibleChangeState()
                            }
                        }
                },
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_logout),
                contentDescription = null,
                tint = colorResource(id = R.color.red)
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Logout From account",
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.red)
            )
        }

    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        LoadedProfileScreen(
            modifier = Modifier.background(PurpleBlack),
            data = ProfileUiState.Loaded(CurrentUserUiModel.unknown),
            onInteraction = {}
        )
    }
}