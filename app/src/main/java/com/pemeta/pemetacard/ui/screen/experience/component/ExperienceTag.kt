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
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.DataPemetaExperience
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ExperienceTag(type: String) {
    val color = if ( type == "Perencanaan Jalan") {
        R.color.road_planing_color
    } else {
        if ( type == "Pengawasan Jalan") {
            R.color.road_supervision_color
        } else {
            if ( type == "Pengawasan Jembatan") {
                R.color.bridge_supervision_color
            } else {
                if ( type == "Perencanaan Jembatan") {
                    R.color.bridge_planning_color
                } else {
                    R.color.other_color
                }
            }
        }
    }

    ChipViewExperienceType(
        experienceType = type,
        colorResource = colorResource(id = color)
    )
}

@Composable
fun ChipViewExperienceType(experienceType: String, colorResource: Color) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .wrapContentWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource.copy(.08f))
            .clickable { Toast.makeText(context, R.string.clickable_dev_detail, Toast.LENGTH_LONG).show() },
    ) {
        Text(
            text = experienceType,
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
        ExperienceTag(typePreview.type)
    }
}