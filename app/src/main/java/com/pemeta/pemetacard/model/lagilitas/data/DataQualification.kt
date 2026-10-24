package com.pemeta.pemetacard.model.lagilitas.data

import com.pemeta.pemetacard.model.lagilitas.model.Qualification

object DataQualification {
    val QualificationList = listOf(
        Qualification(
            id = 99,
            code = "null",
            name = "null"
        ),
        Qualification(
            id = 1,
            code = "K",
            name = "Kecil"
        ),
        Qualification(
            id = 2,
            code = "M",
            name = "Menengah"
        ),
        Qualification(
            id = 3,
            code = "B",
            name = "Besar"
        ),
    )
}