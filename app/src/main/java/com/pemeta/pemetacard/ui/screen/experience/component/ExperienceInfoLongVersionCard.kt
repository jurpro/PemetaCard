package com.pemeta.pemetacard.ui.screen.experience.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.pengalaman.coredata.DataPemetaExperience
import com.pemeta.pemetacard.model.pengalaman.model.CompanyExperience
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ExperienceInfoLongVersionCard(
    title: String,
    value: String,
    clickable: () -> Unit = {},
    navController: NavController = rememberNavController(),
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .width(200.dp)
            .heightIn(min = 100.dp, max = 160.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = MaterialTheme.colorScheme.errorContainer)
            .padding(8.dp)
          //  .clickable { navController.navigate(ScreenMenu.DetailInfo.createRoute(infoId = experience.id)) },
           // .clickable { Toast.makeText(context, R.string.clickable_dev_detail, Toast.LENGTH_LONG).show() },
            .clickable {
                if (clickable != null) {
                    clickable()
                } else {
                    Toast.makeText(context, R.string.clickable_dev_detail, Toast.LENGTH_LONG).show()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.wrapContentWidth()
        ) {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onErrorContainer,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = value,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EExperienceInfoLongVersionCardPreview(){
    PemetaCardTheme {
        val experienceInfoCardDataPreview = DataPemetaExperience.CompanyExperienceList[0]
        val period = experienceInfoCardDataPreview.periodBegin.plus(" - ").plus(experienceInfoCardDataPreview.periodEnd)

        ExperienceInfoLongVersionCard(
           // experience = DataPemetaExperience.CompanyExperienceList[0],
            title = stringResource(id = R.string.detail_experience_period),
            value = period
        )
    }
}