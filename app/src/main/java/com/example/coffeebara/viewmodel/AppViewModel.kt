package com.example.coffeebara.viewmodel

import android.bluetooth.BluetoothClass.Device
import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.coffeebara.data.DeviceInfo
import com.example.coffeebara.data.UserInfo
import com.example.coffeebara.data.dto.CreateUserRequest
import com.example.coffeebara.repository.MainRepository
import com.example.coffeebara.repository.MessageOfCreateUser
import com.example.coffeebara.repository.NetworkResult
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.log

class AppViewModel :ViewModel() {

    val repository : MainRepository = MainRepository()

    var deviceList = mutableListOf<DeviceInfo>(
        DeviceInfo(1, "청심대", 37.542344, 127.076906, 30, "관리자1"),
        DeviceInfo(2, "공학관 A동", 37.541943, 127.078804, 70, "관리자1"),
        DeviceInfo(3, "새천년관", 37.543513, 127.077337, 10, "관리자2"),
        DeviceInfo(4, "경영관", 37.544397, 127.076345, 40, "관리자2"),
        DeviceInfo(5, "산학협동관", 37.539745, 127.073139, 50, "관리자2"),
        DeviceInfo(6, "예술디자인관", 37.542861, 127.073062, 80, "관리자2"),
        DeviceInfo(7, "도서관 자료실", 37.542064, 127.073570, 20, "관리자2"),
        DeviceInfo(8, "도서관 열람실", 37.542064, 127.074118, 95, "관리자2"),
        DeviceInfo(9, "수의대", 37.539199, 127.074609, 5, "관리자2"),
        DeviceInfo(10, "과학관", 37.541581, 127.080337, 100, "관리자2"),
    )

    var userInfo = mutableStateOf<UserInfo>(
        UserInfo(1,"장시게이", 37.54, 127.07, "manager")
    )

    fun login(userId: String, userPassword: String, navController:NavController, snackBarHostState: SnackbarHostState){

    }

    private var userId : Long? = null

    suspend fun createUser(id : String, password : String, name: String): MessageOfCreateUser?{
        val createUserRequest = CreateUserRequest(
            id = id,
            password = password,
            name = name,
            latitude = 37.541631,
            longitude = 127.076575
        )

        return viewModelScope.async {
            val result = repository.createUserResponse(createUserRequest)
            userId = result?.userId
            result
        }.await()
    }
}