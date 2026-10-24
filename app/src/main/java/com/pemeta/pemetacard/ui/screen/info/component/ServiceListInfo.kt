package com.pemeta.pemetacard.ui.screen.info.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.model.pengalaman.data.DataJobType
import com.pemeta.pemetacard.model.pengalaman.data.ServicesData
import com.pemeta.pemetacard.ui.theme.EncodeSans
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme
import com.pemeta.pemetacard.ui.theme.WarmGold
import kotlin.collections.get

@Composable
fun ServiceListInfo(
    navController: NavController,
    modifier: Modifier = Modifier
){
    // val listServiceWork = ServicesData.listServicesData
    val listServiceWork = DataJobType.JobTypeList

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "\u2022 What We do".uppercase(),
            modifier = Modifier.padding(8.dp),
            fontFamily = EncodeSans,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = WarmGold
        )

        Text(
            text = "Services",
            modifier = Modifier.padding(start = 8.dp),
            fontFamily = EncodeSans,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 30.sp,
            color = WarmGold
        )

        Spacer(Modifier.padding(4.dp))

        listServiceWork.forEach { service ->
            ServiceInfoCard(
                icon = "\u25C8",
                title = service.name,
                id = service.id,
                description = service.description.toString(),
                navController = navController,
                modifier = Modifier
                    .padding(top = 4.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = PIXEL_9_PRO)
fun ServiceListInfoPreview(){
    PemetaCardTheme {
        ServiceListInfo(
            navController = rememberNavController()
        )
    }
}