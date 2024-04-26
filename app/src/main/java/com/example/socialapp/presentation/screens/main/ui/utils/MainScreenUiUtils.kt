package com.example.socialapp.presentation.screens.main.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.socialapp.R
import com.example.socialapp.presentation.models.CurrentUserUiModel
import com.example.socialapp.presentation.models.UsersListUiModel
import com.example.socialapp.presentation.theme.PurpleBlack
import com.example.socialapp.presentation.theme.UserBorderColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenToolbar(
    modifier: Modifier = Modifier,
    userName: String,
) {
    TopAppBar(
        modifier = modifier.padding(horizontal = 14.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PurpleBlack
        ),
        title = {
            Text(
                text = "Good Morning, $userName.",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                lineHeight = 24.sp,
                color = Color.White
            )
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.message_icon),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    tint = Color.White
                )
            }
        },
    )
}

@Composable
fun AllUsersBlock(
    modifier: Modifier = Modifier,
    allUsers: List<CurrentUserUiModel>,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(1f),
                text = "Other Users",
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                fontSize = 16.sp
            )

            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_right),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            items(
                items = allUsers,
                key = { item -> item.objectId },
            ) { user ->
                UserItem(
                    userImageUrl = user.userAvatar.url,
                    userName = "${user.firstName} ${user.lastName}",
                )
            }
        }
    }
}

@Composable
fun UserItem(
    userImageUrl: String,
    userName: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .width(120.dp)
            .height(160.dp)
            .padding(10.dp, top = 0.dp)
            .clip(RoundedCornerShape(16.dp)), contentAlignment = Alignment.BottomCenter
    ) {

        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(52.dp)
                    .border(1.dp, UserBorderColor, CircleShape)
                    .clip(CircleShape)
                    .fillMaxWidth(),
                model = userImageUrl,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Text(
                text = userName,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun MainScreenToolbarPreview() {
    MainScreenToolbar(
        userName = "Abdullokh"
    )
}

@Preview
@Composable
fun UserItemPreview() {
    MaterialTheme {
        AllUsersBlock(allUsers = UsersListUiModel.usersPreview())
    }
}
