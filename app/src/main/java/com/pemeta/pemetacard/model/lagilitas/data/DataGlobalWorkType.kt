package com.pemeta.pemetacard.model.lagilitas.data

import com.pemeta.pemetacard.model.pengalaman.model.GlobalWorkType

object DataGlobalWorkType {
    val GlobalWorkTypeList = listOf(
        GlobalWorkType(
            id = 1,
            typeWork = "Perencanaan"
        ),
        GlobalWorkType(
            id = 2,
            typeWork = "Pengawasan"
        ),
        GlobalWorkType(
            id = 3,
            typeWork = "Semua"
        ),
        GlobalWorkType(
            id = 4,
            typeWork = "On Going"
        )
    )
}