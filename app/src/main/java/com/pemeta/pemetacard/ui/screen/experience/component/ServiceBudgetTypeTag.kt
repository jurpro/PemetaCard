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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.pengalaman.coredata.DataPemetaExperience
import com.pemeta.pemetacard.model.pengalaman.model.ServiceBudget
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ServiceBudgetTypeTag(
    serviceBudget: ServiceBudget,
    navController: NavController
) {
    val serviceBudgetType = serviceBudget.code

    val color = if ( serviceBudgetType == "APBN") {
        R.color.orange_500
    } else {
        if ( serviceBudgetType == "APBD") {
            R.color.green_light
        } else {
            if ( serviceBudgetType == "Swasta") {
                R.color.green_orange
            } else {
                R.color.other_color
            }
        }
    }

    ChipViewServiceBudgetType(
        serviceBudget = serviceBudget,
        colorResource = colorResource(id = color),
        navController = navController
    )
}

@Composable
fun ChipViewServiceBudgetType(
    serviceBudget: ServiceBudget,
    colorResource: Color,
    navController: NavController
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .wrapContentWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource.copy(.08f))
            .clickable { navController.navigate(ScreenMenu.DetailService.createRoute(serviceId = serviceBudget.id)) },
           /* .clickable {
                Toast.makeText(
                    context,
                    R.string.clickable_budget_detail.toString().plus(serviceBudgetType),
                    Toast.LENGTH_LONG).show()
            }, */
    ) {
        Text(
            text = serviceBudget.code,
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
            serviceBudget = serviceBudgetPreview.serviceBudget,
            navController = rememberNavController()
        )
    }
}