package com.pemeta.pemetacard.model

data class Client(
    val id: Int,
    val code: String,
    val name: String,
    val province: String,
    val address: String,
    val image: Int,
    val bio: String,
)
