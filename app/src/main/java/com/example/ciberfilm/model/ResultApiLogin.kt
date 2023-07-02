package com.example.ciberfilm.model

data class ResultApiLogin(
    val email: String,
    val fullName: String,
    val password: String,
    val id: Int,
    val status: Boolean
)
