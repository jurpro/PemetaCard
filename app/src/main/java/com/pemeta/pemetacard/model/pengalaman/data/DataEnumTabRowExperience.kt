package com.pemeta.pemetacard.model.pengalaman.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.pemeta.pemetacard.model.pengalaman.model.JobFinalType

enum class DataEnumTabRowExperience (
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val textTabExperience: JobFinalType,
    val text: String
) {
    Semua(
        unselectedIcon = Icons.Outlined.ShoppingCart,
        selectedIcon = Icons.Filled.ShoppingCart,
        textTabExperience = DataJobFinalType.JobFinalTypeList[0],
        text = "Shop"
    ),
    Finished(
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite,
        textTabExperience = DataJobFinalType.JobFinalTypeList[1],
        text = "Favourite"
    ),
    OnGoing(
        unselectedIcon = Icons.Outlined.Person,
        selectedIcon = Icons.Filled.Person,
        textTabExperience = DataJobFinalType.JobFinalTypeList[2],
        text = "Profile"
    )
}