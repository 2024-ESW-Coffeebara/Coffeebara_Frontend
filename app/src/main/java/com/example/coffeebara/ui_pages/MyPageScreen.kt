package com.example.coffeebara.ui_pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.coffeebara.R
import com.example.coffeebara.navigation.LocalNavGraphViewModelStoreOwner
import com.example.coffeebara.ui.theme.BottomBar
import com.example.coffeebara.ui.theme.DarkBrown
import com.example.coffeebara.ui.theme.White
import com.example.coffeebara.ui_modules.BottomNavigationBar
import com.example.coffeebara.ui_modules.MyPageTopBar
import com.example.coffeebara.viewmodel.AppViewModel

@Composable
fun MyPageScreen(navController : NavController) {

    val appViewModel: AppViewModel =
        viewModel(viewModelStoreOwner = LocalNavGraphViewModelStoreOwner.current)

    Scaffold (
        bottomBar = { BottomNavigationBar(navController) },
        topBar = { MyPageTopBar() }
    ){contentPadding ->
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxWidth()
                .padding(
                    horizontal = 30.dp
                )
        ){
            Icon(
                painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = "프로필 이미지",
                tint = White,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(100.dp)
                    .background(
                        color = BottomBar,
                        shape = CircleShape
                    )
            )
            Text(
                text = appViewModel.userInfo.component1().name,
                modifier = Modifier
                    .padding(top = 15.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Row {

            }
        }
    }
}