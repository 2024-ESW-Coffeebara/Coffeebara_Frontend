package com.example.coffeebara.repository

import android.util.Log
import com.example.coffeebara.data.dto.CreateUserRequest
import com.example.coffeebara.retrofit.RetrofitClient
import com.google.gson.Gson

sealed class NetworkResult<out T>{
    data class Success<out T>(val data:T) : NetworkResult<T>()
    data class Error(val exception: Exception) : NetworkResult<Nothing>()
}

data class ErrorResponse(val message: String)
data class MessageOfCreateUser(
    val userId : Long,
    val message : String?,
    val isSuccess : Boolean
)

class MainRepository {

    suspend fun createUserResponse(createUserRequest: CreateUserRequest) : MessageOfCreateUser? {
        return try{
            val response = RetrofitClient.getAPIService.createUser(createUserRequest)
            if(response.isSuccessful){
                val body = response.body()
                if(body != null){
                    val result = body.result
                    result?.let { MessageOfCreateUser(it.userId, response.body()?.message!!, true) }
                }
                else{
                    Log.d("im working", "body가 본문에 없습니다.")
                    null
                }
            } else {
                Log.d("im working", "HTTP 요청이 실패하였습니다. 코드: ${response.code()}, 메시지: ${response.message()}")
                val errorBodyString = response.errorBody()?.string()
                val errorMessage = if (!errorBodyString.isNullOrBlank()) {
                    val gson = Gson()
                    val errorResponse = gson.fromJson(errorBodyString, ErrorResponse::class.java)
                    errorResponse.message
                } else {
                    "서버에서 에러 메시지를 가져올 수 없습니다."
                }
                MessageOfCreateUser(0L, errorMessage, false)
            }
        }catch(e: Exception){
            Log.e("im working" , e.toString())
            null
        }
    }


}