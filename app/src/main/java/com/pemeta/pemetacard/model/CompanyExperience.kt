package com.pemeta.pemetacard.model

data class CompanyExperience(
    val id: Int,
    val code: String,
    val type: String,
    val title: String,
    val yearBegin: Int,
    val yearEnd: Int,
    val period: String,
    val contractAmount: String,
    val contractNumber: String,
    val contractDate: String,
    val serviceBudget: String,
    val partner: String,
    val location: String,
    val image: Int,
    val description: String,
    val ourClient: Client,
)
