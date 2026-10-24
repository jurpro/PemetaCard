package com.pemeta.pemetacard.ui.screen.experience.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.pengalaman.coredata.DataPemetaExperience
import com.pemeta.pemetacard.model.pengalaman.model.JobType
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ExperienceTag(
   // type: String,
    experienceType: JobType,
    navController: NavController
) {
    val jobType = experienceType.name

    val color = if ( jobType == "Perencanaan Jalan") {
        R.color.road_planing_color
    } else {
        if ( jobType == "Pengawasan Jalan") {
            R.color.road_supervision_color
        } else {
            if ( jobType == "Pengawasan Jembatan") {
                R.color.bridge_supervision_color
            } else {
                if ( jobType == "Perencanaan Jembatan") {
                    R.color.bridge_planning_color
                } else {
                    R.color.other_color
                }
            }
        }
    }

    ChipViewExperienceType(
        experienceType = experienceType,
        colorResource = colorResource(id = color),
        navController = navController
    )
}

@Composable
fun ChipViewExperienceType(
    experienceType: JobType,
    colorResource: Color,
    navController: NavController
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .wrapContentWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource.copy(.08f))
            .clickable { navController.navigate(ScreenMenu.DetailType.createRoute(typeId = experienceType.id)) },
           // .clickable { Toast.makeText(context, R.string.clickable_dev_detail, Toast.LENGTH_LONG).show() },
    ) {
        Text(
            text = experienceType.name,
            modifier = Modifier
                .padding(12.dp, 6.dp, 12.dp, 6.dp)
                .width(85.dp),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = colorResource
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExperienceTagPreview(){

    val typePreview = DataPemetaExperience.CompanyExperienceList[2]

    PemetaCardTheme {
        ExperienceTag(
            experienceType = typePreview.type,
            navController = rememberNavController()
        )
    }
}