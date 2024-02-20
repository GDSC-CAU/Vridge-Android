package com.gdsc_cau.vridge.ui.profile

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gdsc_cau.vridge.R
import com.gdsc_cau.vridge.data.models.User
import com.gdsc_cau.vridge.ui.login.LoginActivity
import com.gdsc_cau.vridge.ui.main.MainActivity
import com.gdsc_cau.vridge.ui.theme.Grey3

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val user = viewModel.user.collectAsStateWithLifecycle().value
    val isLoggedOut = viewModel.isLoggedOut.collectAsStateWithLifecycle().value

    val context = LocalContext.current

    ProfileList(profileData = user, viewModel = viewModel)

    LaunchedEffect(key1 = isLoggedOut) {
        if (isLoggedOut) {
            context.startActivity(Intent(context, LoginActivity::class.java))
            (context as Activity).finish()
        }
    }
}

@Composable
fun ProfileList(profileData: User, viewModel: ProfileViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        ProfileListItem(
            title = stringResource(id = R.string.profile_list_item_name),
            content = profileData.name
        )
        ProfileListDivider()
        ProfileListItem(
            title = stringResource(id = R.string.profile_list_item_email),
            content = profileData.email
        )
        ProfileListDivider()
        ProfileListItem(
            title = stringResource(id = R.string.profile_list_item_synthesize_cnt),
            content = profileData.cntVoice.toString()
        )
        ProfileListDivider()
        ProfileListItem(
            title = stringResource(id = R.string.profile_list_item_signout_title),
            content = stringResource(id = R.string.profile_list_item_signout_description),
            clickable = true,
            onClick = {
                viewModel.signOut()
            }
        )
        ProfileListDivider()
        ProfileListItem(
            title = stringResource(id = R.string.profile_list_item_delete_title),
            content = stringResource(id = R.string.profile_list_item_delete_description),
            onClick = {
                viewModel.unregister()
            }
        )
    }
}

@Composable
fun ProfileListDivider() {
    Divider(
        color = Grey3
    )
}

@Composable
fun ProfileListItem(
    title: String,
    content: String,
    clickable: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .clickable(enabled = clickable) { onClick() }
            .fillMaxWidth()
            .padding(all = 15.dp)
    ) {
        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = content,
            fontSize = 17.sp
        )
    }
}
