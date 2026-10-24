package com.pemeta.pemetacard.model.pengalaman.data

import com.pemeta.pemetacard.model.pengalaman.model.JobType

object DataJobType {
    val JobTypeList= listOf(
        JobType(
            id = 1,
            code = "PRC01",
            name = "Perencanaan Jalan dan Jembatan",
            description = "Pengalaman perusahaan dalam melaksanakan pekerjaan Perencanaan Preservasi / Pembangunan Jalan dan Jembatan. ",
            headListJob = "Berikut adalah daftar pengalaman perusahaan dalam melaksanakan pekerjaan Perencanaan Jalan dan Jembatan selama 15 tahun terakhir."
        ),
        JobType(
            id = 2,
            code = "PRC02",
            name = "Perencanaan Jalan",
            description = "Pengalaman perusahaan dalam melaksanakan pekerjaan Perencanaan Preservasi / Pembangunan Jalan. ",
            headListJob = "Berikut adalah daftar pengalaman perusahaan dalam melaksanakan pekerjaan Perencanaan Jalan selama 15 tahun terakhir."
        ),
        JobType(
            id = 3,
            code = "PRC03",
            name = "Perencanaan Jembatan",
            description = "Pengalaman perusahaan dalam melaksanakan pekerjaan Perencanaan Preservasi / Pembangunan Jembatan, terutama Jembatan khusus Bentang Panjang. ",
            headListJob = "Berikut adalah daftar pengalaman perusahaan dalam melaksanakan pekerjaan Perencanaan Jembatan selama 15 tahun terakhir."
        ),
        JobType(
            id = 4,
            code = "PWS01",
            name = "Pengawasan Jalan dan Jembatan",
            description = "Pengalaman perusahaan dalam melaksanakan pekerjaan Pengawasan Preservasi / Pembangunan Jalan dan Jembatan, umumnya berupa Long Segment.",
            headListJob = "Berikut adalah daftar pengalaman perusahaan dalam melaksanakan pekerjaan Pengawasan Jalan dan Jembatan selama 15 tahun terakhir."
        ),
        JobType(
            id = 5,
            code = "PWS02",
            name = "Pengawasan Jalan",
            description = "Pengalaman perusahaan dalam melaksanakan pekerjaan Pengawasan Preservasi / Pembangunan Jalan, umumnya berupa Long Segment khusus Jalan.",
            headListJob = "Berikut adalah daftar pengalaman perusahaan dalam melaksanakan pekerjaan Pengawasan Jalan selama 15 tahun terakhir."
        ),
        JobType(
            id = 6,
            code = "PWS03",
            name = "Pengawasan Jembatan",
            description = "Pengalaman perusahaan dalam melaksanakan pekerjaan Pengawasan Preservasi / Pembangunan Jembatan, umumnya berupa Jembatan Khusus Bentang Panjang.",
            headListJob = "Berikut adalah daftar pengalaman perusahaan dalam melaksanakan pekerjaan Pengawasan Jembatan selama 15 tahun terakhir."
        ),
    /*    JobType(
            id = 7,
            code = "PRC19",
            name = "Perencanaan Lainnya",
            description = "Pengalaman perusahaan dalam pekerjaan perencanaan lainnya."
        ),
        JobType(
            id = 8,
            code = "PWS19",
            name = "Pengawasan Lainnya",
            description = "Pengalaman perusahaan dalam pekerjaan pengawasan lainnya."
        ),
        JobType(
            id = 9,
            code = "CONS99",
            name = "Lainnya",
            description = "Pengalaman perusahaan dalam pekerjaan lainnya."
        ) */
    )
}