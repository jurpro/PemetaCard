package com.pemeta.pemetacard.model.lagilitas.model

data class Legalitas(
    val id: Int,
    val code: String,
    val name: String,
    val number: String,
    val agency: String,
    val qualification: Qualification,
    val classification: Classification,
    val kbli_number: KbliNumber?,
    val subClassification: SubClassification?,
    val date_of_issue: String,
    val valid_until: String,
    val isValid: Boolean,
    val image: Int,
    val icon: Int
)
