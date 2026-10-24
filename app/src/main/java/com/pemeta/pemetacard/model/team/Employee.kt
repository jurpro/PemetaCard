package com.pemeta.pemetacard.model.team

data class Employee(
    val id: Int,
    val name: String,
    val photo: Int,
    val position: String,
    val since: String,
    val nickName: String,

    val isTopManagement: Boolean = false,
    val isDEDTeam: Boolean = false,
    val isSupervisionTeam: Boolean = false,
    val isActive: Boolean = false,
    val description: String = "",

    val address: String = "",
    val cityProvince: List<Int> = listOf(3273, 32),

    val education: String = "",
    val skillProject: String = "",
    val projectHandle: List<Int>? = null,
    val projectOutside: List<Int>? = null,

    val instagram: String = "",
    val twitter: String = "",
    val whatsapp: String = "",
    val personQuote: String = "",
)