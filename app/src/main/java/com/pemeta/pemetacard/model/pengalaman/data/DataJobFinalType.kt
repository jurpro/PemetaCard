package com.pemeta.pemetacard.model.pengalaman.data

import com.pemeta.pemetacard.model.pengalaman.model.JobFinalType

object DataJobFinalType {
    val JobFinalTypeList = listOf(
        JobFinalType(
            id = 1,
            name = "All"
        ),
        JobFinalType(
            id = 2,
            name = "Finished"
        ),
        JobFinalType(
            id = 3,
            name = "On Going"
        )
    )
}