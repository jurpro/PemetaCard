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
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.DataPemetaExperience
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ServiceBudgetTypeTag(serviceBudget: String) {

    val color = if ( serviceBudget == "APBN") {
        R.color.orange_500
    } else {
        if ( serviceBudget == "APBD") {
            R.color.green_light
        } else {
            if ( serviceBudget == "Swasta") {
                R.color.green_orange
            } else {
                R.color.other_color
            }
        }
    }

    ChipViewServiceBudgetType(
        serviceBudgetType = serviceBudget,
        colorResource = colorResource(id = color)
    )
}

@Composable
fun ChipViewServiceBudgetType(serviceBudgetType: String, colorResource: Color) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .wrapContentWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource.copy(.08f))
            .clickable {
                Toast.makeText(
                    context,
                    R.string.clickable_budget_detail.toString().plus(serviceBudgetType),
                    Toast.LENGTH_LONG).show()
            },
    ) {
        Text(
            text = serviceBudgetType,
            modifier = Modifier
                .padding(12.dp, 6.dp, 12.dp, 6.dp)
                .width(85.dp),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = colorResource
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ServiceBudgetTypeTagPreview (){
    PemetaCardTheme {

        val serviceBudgetPreview = DataPemetaExperience.CompanyExperienceList[0]

        ServiceBudgetTypeTag(
            serviceBudget = serviceBudgetPreview.serviceBudget
        )
    }
}