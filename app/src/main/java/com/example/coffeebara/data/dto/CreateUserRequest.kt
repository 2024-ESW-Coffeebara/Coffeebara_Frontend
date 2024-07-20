package com.example.coffeebara.data.dto

data class CreateUserRequest(
    val id : String,
    val password : String,
    val name : String,
    val latitude : Double,
    val longitude : Double
)