package com.pemeta.pemetacard.model.partner

import com.pemeta.pemetacard.R


data class Partner(
    val id: Int,
    val code: String,
    val name: String? = null,
    val province: String? = null,
    val address: String? = null,
    val image: Int? = R.drawable.image_project_without_picture,
    val bio: String? = null,
    val director: String? = null,
    val contactPerson: String? = null
)
