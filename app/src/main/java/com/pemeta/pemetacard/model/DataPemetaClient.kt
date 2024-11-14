package com.pemeta.pemetacard.model

import com.pemeta.pemetacard.R

object DataPemetaClient {
    val ClientList = listOf(
        Client(
            id = 1,
            code = "P2JN Prov. DIY",
            name = "Satker Perencanaan dan Pengawasan Jalan Nasional Prov. DIY",
            province = "Prov. D.I. Yogyakarta",
            address = "Jl. Padjajaran / Ring Road Utara KM. 1,5 Depok, Maguwoharjo, Kab. Sleman Prov. DIY 55282",
            image = R.drawable.logo_kementerian_pupr,
            bio = "PPK Pengawasan P2JN Prov. DIY",
        ),
        Client(
            id = 2,
            code = "P2JN Prov. Jabar",
            name = "Satker Perencanaan dan Pengawasan Jalan Nasional Prov. Jawa Barat",
            province = "Prov. Jawa Barat",
            address = "Jl. A.H Nasution No. 308 Ujungberung – Bandung 40608",
            image = R.drawable.logo_kementerian_pupr,
            bio = "PPK Pengawasan P2JN Prov. Jawa Barat",
        ),
        Client(
            id = 3,
            code = "DBMSDA Kab. Kukar",
            name = "Dinas Bina Marga dan Sumber Daya Air Kabupaten Kutai Kartanegara",
            province = "Prov. Kalimantan Timur",
            address = "Jl. AP Mangkunegoro Kelurahan Timbau Tenggarong Kab. Kutai Kartanegara 75511",
            image = R.drawable.logo_kab_kutai_kertanegara,
            bio = "PPK Pengawasan Teknis Pembangunan Jembatan Kutai Kartanegara",
        ),
        Client(
            id = 4,
            code = "DPU Kab. Kukar",
            name = "Dinas Pekerjaan Umum Bidang Bina Marga Kabupaten Kutai Kartanegara",
            province = "Prov. Kalimantan Timur",
            address = "Komplek Perkantoran Bupati Kutai Kartanegara, Tenggarong Kab. Kutai Kartanegara 75511",
            image = R.drawable.logo_kab_kutai_kertanegara,
            bio = "PPK Perencanaan Jembatan Sebulu",
        ),
        Client(
            id = 5,
            code = "DPU Prov. Kalsel",
            name = "Dinas Pekerjaan Umum Bidang Bina Marga Provinsi Kalimantan Selatan",
            province = "Prov. Kalimantan Selatan",
            address = "Jl. Dharma Praja Kawasan Perkantoran Pemerintah Provinsi Kalimantan Selatan",
            image = R.drawable.logo_provinsi_kalimantan_selatan,
            bio = "Kepala Bidang Bina Marga Selaku Kuasa Pengguna Anggaran (KPA)",
        ),
    )

}