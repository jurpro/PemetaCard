package com.pemeta.pemetacard.model.pengalaman.model

import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.client.Client
import com.pemeta.pemetacard.model.maps.Coordinates
import com.pemeta.pemetacard.model.partner.Partner

data class CompanyExperience(
    val id: Int,
    val code: String = "",
    val idSimpan: String? = null,
    val typeGlobal: GlobalWorkType,
    val type: JobType,
    val title: String,
    val yearBegin: Int = 0,
    val yearEnd: Int = 0,
    val periodBegin: String = "",
    val periodEnd: String = "",
    val contractAmount: Long,
    val contractNumber: String = "",
    val contractDate: String = "",
    val serviceBudget: ServiceBudget,
    val isStandAlone: Boolean = false,
    val isLeader: Boolean = true,
    val partner1: Partner? = null,
    val partner2: Partner? = null,
    val partner3: Partner? = null,
    val partner4: Partner? = null,
    val partner5: Partner? = null,
   // val location: String = "",
    val location: List<Int> = listOf(32),
    val image: Int = R.drawable.image_project_without_picture,
    val description: String = "",
   // val onGoingProject: Boolean,
    val isFinished: Boolean = true,
    val ourClient: Client,
    val latitude: Double = -0.9606049109901842,
    val longitude: Double = 116.69949276549401,
    val coordinate: List<Coordinates> = listOf(
        Coordinates(
            latitude = -0.9606049109901842,
            longitude= 116.69949276549401
        )
    ),
)
