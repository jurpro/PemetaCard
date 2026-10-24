package com.pemeta.pemetacard.model.newsinfo.model

import com.pemeta.pemetacard.model.pengalaman.model.CompanyExperience

data class NewsInfo(
    val id: Int,
    val code: String? = null,
    val title: String,
    val headlineTitle: String? = null,
    val date: String? = null,
    val location: String,
    val description: String? = null,
    val image: Int,
    val project: CompanyExperience? = null
)
