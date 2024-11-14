package com.pemeta.pemetacard.model

import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.DataPemetaClient.ClientList

object DataPemetaExperience {

    val CompanyExperienceList = listOf(
        CompanyExperience(
            id = 1,
            code = "Paket 5",
            type = "Pengawasan Jalan",
            title = "Paket 5 Pengawasan Pembangunan Jalan Planjan-Baron-Tepus (MYC)",
            yearBegin = 2020,
            yearEnd = 2022,
            period = "21 Desember 2020 - 20 Desember 2022",
            contractAmount = "Rp. 4.539.761.000,00",
            contractNumber = "HK.02.01-P2JN.DIY/PS/25861",
            contractDate = "17 November 2020",
            serviceBudget = "APBN",
            partner = "PT. Anugerah Kridapradana \nPT. Arkade Gahana Konsultan (JO)",
            location = "Kab. Gunungkidul Prov. DIY",
            image = R.drawable.menteri_pupr_baron_tepus,
            description = "Pekerjaan pengawasan ini merupakan pekerjaan fisik yang membangun jalur baru dengan menyusuri pantai selatan pulau Jawa di wilayah Provinsi D.I Yogyakarta. Jalur ini dikenal dengan nama \"Jalur Lintas Selatan\" atau JLS. Pembangunannya membutuhkan waktu sekitar 2 tahun untuk diselesaikan dengan baik dan sempurna.",
            ourClient = ClientList[0]
        ),
        CompanyExperience(
            id = 2,
            code = "PW 2013 Kukar",
            type = "Pengawasan Jembatan",
            title = "Pengawasan Pembangunan Jembatan Kutai Kartanegara (Multiyears)",
            yearBegin = 2013,
            yearEnd = 2015,
            period = "09 April 2013 - 30 November 2015",
            contractAmount = "Rp. 7.695.490.000,00",
            contractNumber = "155/630/DBM-SDA/IV/2013",
            contractDate = "09 April 2013",
            serviceBudget = "APBD",
            partner = "Tunggal",
            location = "Kab. Kutai Kartanegara,\nProv. Kalimantan Timur",
            image = R.drawable.kukar_pemeta_77thn_ri_kecil,
            description = "Pekerjaan pengawasan pembangunan jembatan ini membutuhkan waktu 32 bulan / 2,7 tahun. Merupakan jembatan pengganti yang sebelumnya mengalami bencana roboh pada tahun 2012.",
            ourClient = ClientList[2]
        ),
        CompanyExperience(
            id = 3,
            code = "PW 10-2023",
            type = "Pengawasan Jembatan",
            title = "PW 10-2023 Pengawasan Pembangunan Jembatan Gantung Provinsi Jawa Barat",
            yearBegin = 2023,
            yearEnd = 2023,
            period = "16 Juni 2023 - 31 Desember 2023",
            contractAmount = "Rp. 3.665.245.530,00",
            contractNumber = "HK.02.03/KTR.2023/PPKWASJBR/10",
            contractDate = "16 Juni 2023",
            serviceBudget = "APBN",
            partner = "PT. Seecons (lead), PT. Puri Dimensi",
            location = "Tersebar 10 lokasi,\nProv. Jawa Barat",
            image = R.drawable.image_project_without_picture,
            description = "Pekerjaan pengawasan pembangunan jembatan gantung ini merupakan proyek unggulan nasional untuk masyarakat Indonesia. Tersebar di 10 lokasi dengan menurunkan 10 field team. PT. Pemeta Engineering System merupakan anggota KSO bersama PT. Puri Dimensi dengan lead firm PT. Seecons.",
            ourClient = ClientList[1]
        ),
        CompanyExperience(
            id = 4,
            code = "PR 2023 Kukar",
            type = "Perencanaan Jembatan",
            title = "Review Desain Jembatan Sebulu",
            yearBegin = 2023,
            yearEnd = 2023,
            period = "22 Juni 2023 - 19 Desember 2023",
            contractAmount = "Rp. 2.874.650.250,00",
            contractNumber = "P.803/DPU/BM/600.1.10.3/6/2023",
            contractDate = "22 Juni 2023",
            serviceBudget = "APBD",
            partner = "Tunggal",
            location = "Kab. Kutai Kartanegara,\nProv. Kalimantan Timur",
            image = R.drawable.desain_jembatan_sebulu,
            description = "Jembatan ini merupakan penghubung Sebulu dan Sirbaya di Kecamatan Sebulu Kabupaten Kutai Kartanegara. Merupakan duplikasi dari Jembatan Kutai Kartanegara.",
            ourClient = ClientList[3]
        ),
        CompanyExperience(
            id = 5,
            code = "PR 2024 Kalsel",
            type = "Perencanaan Jembatan",
            title = "Paket Review DED Jembatan Pulau Kalimantan - Pulau Laut",
            yearBegin = 2024,
            yearEnd = 2024,
            period = "31 Januari 2024 - 28 Juli 2024",
            contractAmount = "Rp. 2.874.650.250,00",
            contractNumber = "602.1/012/BM.1718.FB/2024",
            contractDate = "31 Januari 2024",
            serviceBudget = "APBD",
            partner = "PT. Anugerah Kridapradana, PT. Widyadaya Bandaran",
            location = "Kab. Kota Baru,\nProv. Kalimantan Selatan",
            image = R.drawable.desain_jembatan_pulau_kalimantan_pulau_laut_kota_baru,
            description = "Jembatan ini merupakan penghubung Pulau Kalimantan dan Pulau Laut di Kota Baru Provinsi Kalimantan Selatan. Review DED ini merupakan pemutakhiran data dan desain dari pekerjaan DED yang dilaksanakan pada tahun 2015 lalu.",
            ourClient = ClientList[4]
        ),
    )
}