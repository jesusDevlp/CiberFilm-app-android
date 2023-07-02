package com.example.ciberfilm.model

import com.google.gson.annotations.SerializedName

data class Customer (
    val id:Int,
    val status:Boolean,
    val email:String,
    val fullName:String,
    val password:String
        )

