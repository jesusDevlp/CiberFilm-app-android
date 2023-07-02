package com.example.ciberfilm.model

data class Sales(
    val id:Int,
    val customerName: String,
    val genre: String,
    val title: String,
    val description:String,
    val dateEvent:String,
    val timeEvent:String,
    val imageUrl:String,
    val place:String,
    val ticketsQuantity:Int,
    val unitPrice:Double,
    val rating:Double,
    val saleDate:String,
    val saleTime:String,
    val operationNumber:String,
    val total:Double,
    val quantity:Int
)
