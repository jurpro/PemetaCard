package com.pemeta.pemetacard.model.client

data class Client(
    val id: Int,
    val code: String,
    val name: String,
    val province: Int,
    val address: String,
    val image: Int,
    val bio: String,
)