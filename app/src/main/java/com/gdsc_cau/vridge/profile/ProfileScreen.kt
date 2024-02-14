package com.gdsc_cau.vridge.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    Text(text = profileData.name)
}
