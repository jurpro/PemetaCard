package com.pemeta.pemetacard.model.newsinfo.data

import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.newsinfo.model.NewsInfo
import com.pemeta.pemetacard.model.pengalaman.coredata.DataPemetaExperience.CompanyExperienceList

object DataNewsInfo {
    val NewsInfoList = listOf(
        // Harap diperhatikan project yang selalu berubah karena update pekerjaan baru masuk data sehingga index berubah

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

        NewsInfo(
            id = 3,
            code = "News Cilangkap",
            headlineTitle = "Kunjungan Kepala BBPJN di Cilangkap",
            title = "Kunjungan Bapak Kabalai BBPJN DKI Jakarta-Jabar di Underpass Mabes TNI Cilangkap",
            date = "2024-11-04 00:00:00",
            location = "Kota Jakarta Timur Prov. DKI Jakarta",
            description = "Kunjungan bapak Kepala Balai Besar Pelaksana Jalan Nasional (BBPJN) DKI Jakarta - Jawa Barat di lokasi pembangunan underpass jalan baru menuju Mabes TNI Cilangkap. Kunjungan ini untuk mengecek perkembangan progress pembangunan underpass yang direncanakan selesai pada tahun 2025.",
            image = R.drawable.news01_kunjungan_kabalai_underpass,
            project = CompanyExperienceList[8]
        ),
        NewsInfo(
            id = 4,
            code = "News JLS",
            headlineTitle = "Kunjungan Menteri PUPR",
            title = "Kunjungan Bapak Menteri PUPR di Planjan-Baron-Tepus",
            date = "2022-09-18 00:00:00",
            location = "Kab. Gunungkidul Prov. DIY",
            description = "Kunjungan bapak menteri PUPR Basuki Hadimulyo di lokasi pekerjaan pembangunan Jalan Lintas Selatan (JLS) untuk meninjau perkembangan pembangunan jalan nasional yang telah berjalan sekitar 2 tahun. Pekerjaan ini selesai pada Desember 2022",
            image = R.drawable.menteri_pupr_baron_tepus,
            project = CompanyExperienceList[16]
        )
    )
}