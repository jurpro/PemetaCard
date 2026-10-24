package com.pemeta.pemetacard.model.newsinfo.data

import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.newsinfo.model.NewsInfo
import com.pemeta.pemetacard.model.pengalaman.coredata.DataPemetaExperience.CompanyExperienceList

object HighLightData {
    val highLightProject = listOf(
        NewsInfo(
            id = 1,
            code = "News Jembatan Kaca",
            headlineTitle = "Kunjungan Direksi di Bogor",
            title = "Pelaksanaan PHO Pekerjaan Fisik dan Pengawasan Pembangunan Jembatan Kaca Bendungan Sukamahi",
            date = "2026-07-29 00:00:00",
            location = "Jembatan Kaca Bendungan Sukamahi - Bogor",
            description = "Kunjungan Direksi PT. Pemeta Engineering System beserta tim Pengawasan dan tim Pelaksana PT. Wijaya Karya, Tbk pada proses PHO Pembangunan Jembatan Kaca Bendungan Sukamahi di Megamendung, Bogor Prov. Jawa Barat",
            image = R.drawable.tim_jembatankacca01,
            project = CompanyExperienceList[5]
        ),

        NewsInfo(
            id = 2,
            code = "News PW 1-2026",
            headlineTitle = "Kunjungan Kepala BBPJN di Pantura",
            title = "Kunjungan Kepala BBPJN DKI Jakarta - Jawa Barat",
            date = "2026-05-27 00:00:00",
            location = "Cikampek - Kab. Karawang Pantura Jawa Barat",
            description = "Kunjungan Kepala Balai Besar Pelaksana Jalan Nasional DKI Jakarta - Jawa Barat ke lokasi pekerjaan di Jalur Pantura, Cikampek Jawa Barat.",
            image = R.drawable.kunjungan_balai_pantura_2026,
            project = CompanyExperienceList[0]
        ),
    )

    val experienceProject = listOf(
        NewsInfo(
            id = 1,
            title = "Jembatan Kaca Bendungan Sukamahi - Bogor",
            image = R.drawable.tim_jembatankacca01,
            location = "Ciawi - Kab. Bogor"
        ),
        NewsInfo(
            id = 2,
            title = "Kunjungan Kepala BBPJN",
            image = R.drawable.kunjungan_balai_pantura_2026,
            location = "Kab. Karawang - Jalur Pantura"
        ),
    )
}