package com.pemeta.pemetacard.model.pengalaman.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.google.android.gms.common.internal.StringResourceValueReader
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.lagilitas.data.DataGlobalWorkType
import com.pemeta.pemetacard.model.pengalaman.model.GlobalWorkType
import com.pemeta.pemetacard.model.pengalaman.model.JobType

enum class DataEnumTabRowExperienceType(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val textTabExperience: GlobalWorkType,
    val text: String
) {
    Semua(
        unselectedIcon = Icons.Outlined.ShoppingCart,
        selectedIcon = Icons.Filled.ShoppingCart,
        textTabExperience = DataGlobalWorkType.GlobalWorkTypeList[2],
        text = "Shop"
    ),
    Perencanaan(
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite,
        textTabExperience = DataGlobalWorkType.GlobalWorkTypeList[0],
        text = "Favourite"
    ),
    Pengawasan(
        unselectedIcon = Icons.Outlined.Person,
        selectedIcon = Icons.Filled.Person,
        textTabExperience = DataGlobalWorkType.GlobalWorkTypeList[1],
        text = "Profile"
    ),
    OnGoing(
        unselectedIcon = Icons.Outlined.Person,
        selectedIcon = Icons.Filled.Person,
        textTabExperience = DataGlobalWorkType.GlobalWorkTypeList[3],
        text = "On Going"
    )
}