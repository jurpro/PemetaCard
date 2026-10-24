package com.pemeta.pemetacard.model.pengalaman.model

data class JobType(
    val id: Int,
    val name: String,
    val code: String? = null,
    val description: String? = null,
    val headListJob: String? = null
)