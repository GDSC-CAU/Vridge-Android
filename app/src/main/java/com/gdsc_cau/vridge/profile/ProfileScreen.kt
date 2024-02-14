package com.gdsc_cau.vridge.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gdsc_cau.vridge.models.Gender
import com.gdsc_cau.vridge.models.User

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
    ProfileListItem(title = "Name", content = profileData.email, onClickFn = {})
}

@Composable
fun ProfileListItem(
    title: String,
    content: String,
    onClickFn: () -> Unit
) {
    Column(
        modifier =
            Modifier
                .clickable { onClickFn() }
                .fillMaxWidth()
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
