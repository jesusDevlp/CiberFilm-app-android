package com.example.ciberfilm.model

data class Movie(
    val id:Int,
    val genre:String,
    val title:String,
    val description:String,
    val place:String,
    val dateEvent:String,
    val timeEvent:String,
    val imageUrl:String,
    val ticketsQuantity:Int,
    val unitPrice:Double,
    val rating:Double,
    val status:String
)
