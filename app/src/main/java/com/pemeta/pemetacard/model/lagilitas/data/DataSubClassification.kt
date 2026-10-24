package com.pemeta.pemetacard.model.lagilitas.data

import com.pemeta.pemetacard.model.lagilitas.model.SubClassification

object DataSubClassification {
    val SubClassificationList= listOf(
        SubClassification(
            id = 99,
            code = "null",
            description = "null"
        ),
        SubClassification(
            id = 1,
            code = "RK001",
            description = "Jasa Rekayasa Konstruksi Bangunan Gedung Hunian & Non Hunian"
        ),
        SubClassification(
            id = 2,
            code = "RK002",
            description = "Jasa Rekayasa Pekerjaan Teknik Sipil Sumber Daya Air"
        ),
        SubClassification(
            id = 3,
            code = "RK003",
            description = "Jasa Rekayasa Pekerjaan Teknik Sipil Transportasi"
        ),
        SubClassification(
            id = 4,
            code = "RK004",
            description = "Jasa Rekayasa Pekerjaan Mekanikal Dalam Bangunan"
        ),
        SubClassification(
            id = 5,
            code = "RK005",
            description = "Jasa Rekayasa Lainnya"
        ),

        SubClassification(
            id = 6,
            code = "Status Terverifikasi",
            description = "-"
        ),
    )
}