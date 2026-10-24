package com.pemeta.pemetacard.model.pengalaman.data

import com.pemeta.pemetacard.model.pengalaman.model.JobType
import com.pemeta.pemetacard.model.pengalaman.model.ServiceBudget

object ServicesData {
    val listServicesData = listOf(
        ServiceBudget(
            id = 1,
            code = "APBN",
            name = "APBN Kementerian",
            description = "Daftar pengalaman perusahaan yang kontraknya bersumber dari pembiayaan APBN Kementerian Pusat. "
        ),
        ServiceBudget(
            id = 2,
            code = "APBD",
            name = "APBD Provinsi",
            description = "Daftar pengalaman perusahaan yang kontraknya bersumber dari pembiayaan APBD Provinsi."
        ),
        ServiceBudget(
            id = 3,
            code = "APBD",
            name = "APBD Kabupaten / Kota",
            description = "Daftar pengalaman perusahaan yang kontraknya bersumber dari pembiayaan APBD Kabupaten / Kota"
        ),
        ServiceBudget(
            id = 4,
            code = "BUMN",
            name = "BUMN Nasional",
            description = "Daftar pengalaman perusahaan yang kontraknya bersumber dari pembiayaan Anggaran Belanja BUMN."
        ),
        ServiceBudget(
            id = 5,
            code = "Swasta",
            name = "Swasta Nasional",
            description = "Daftar pengalaman perusahaan yang kontraknya bersumber dari pembiayaan Swasta Nasional"
        ),
        ServiceBudget(
            id = 6,
            code = "Loan",
            name = "World Bank / Loan / Dana Luar Negeri",
            description = "Daftar pengalaman perusahaan yang kontraknya bersumber dari pembiayaan Loan, World Bank, maupun dana bantuan luar negeri yang penyalurannya melalui kementerian"
        ),
    )
}