package com.example.ciberfilm.model

data class RegisterCustomerRequest(
    val email:String,
    val fullName: String,
    val password: String
)
