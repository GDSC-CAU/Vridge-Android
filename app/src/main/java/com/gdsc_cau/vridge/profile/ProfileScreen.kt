package com.gdsc_cau.vridge.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdsc_cau.vridge.models.Gender
import com.gdsc_cau.vridge.models.User
import com.gdsc_cau.vridge.ui.theme.Grey3

val profileDummy =
    User(
        cntVoice = 0,
        email = "hello_vridge@gmail.com",
        gender = Gender.MALE,
        name = "Vridge Test User",
        uid = "VRIDGE_TEST_USER_UID"
    )

@Composable
fun ProfileScreen() {
    ProfileList(profileData = profileDummy)
}

@Composable
fun ProfileList(profileData: User) {
    Column(
        modifier =
            Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        ProfileListItem(title = "Name", content = profileData.name)
        ProfileListDivider()
        ProfileListItem(title = "Email", content = profileData.email, onClickFn = {})
        ProfileListDivider()
        ProfileListItem(title = "Synthesized Count", content = profileData.cntVoice.toString(), onClickFn = {})
        ProfileListDivider()
        ProfileListItem(title = "Sign Out", content = "Sign Out from Vridge Account", onClickFn = {})
        ProfileListDivider()
        ProfileListItem(title = "Delete Account", content = "Delete Account from Vridge Account", onClickFn = {})
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
    onClickFn: (() -> Unit)? = null
) {
    Column(
        modifier =
            Modifier
                .clickable { onClickFn?.invoke() }
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
