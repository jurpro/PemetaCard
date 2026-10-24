package com.pemeta.pemetacard.ui.screen.team.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pemeta.pemetacard.model.pengalaman.model.CompanyExperience
import com.pemeta.pemetacard.model.team.Employee
import com.pemeta.pemetacard.model.team.teamcv.ProjectHandlePemeta
import com.pemeta.pemetacard.model.team.Team
import com.pemeta.pemetacard.model.team.teamcv.ProjectOutsidePemeta
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun CurriculumVitaePemeta(
    employee: Employee,
   // curriculumCode: List<Int>?,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current

    val projectCodePemeta: List<CompanyExperience>? = employee.projectHandle?.map { key ->
        (ProjectHandlePemeta.projectHandlePerson[key] ?: "Belum ada pengalaman di Pemeta ($key)") as CompanyExperience
    }
    val projectCodeOutside: List<String>? = employee.projectOutside?.map { keyOutside ->
        (ProjectOutsidePemeta.ProjectOutsideData[keyOutside] ?: "Tidak ada data pengalaman diluar Pemeta ($keyOutside)")
    }
    val cvInPemeta = projectCodePemeta ?: "Belum ada pengalaman profesional di Pemeta"
    var projectName = String
    val projectPosition = String

    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp)
            .clip(RoundedCornerShape(12.dp))
            .padding(8.dp)
        // .clickable { Toast.makeText(context, R.string.clickable_dev_detail, Toast.LENGTH_LONG).show() },
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentWidth()
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 16.dp,
                    bottom = 16.dp
                )
        ) {
            // Pengalaman Kerja bersama Pemeta
            Text(
                text = "Pengalaman Kerja di Pemeta",
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onErrorContainer,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            if (projectCodePemeta == null) {
                Text(
                    text = "Menjabat sebagai ${employee.position} sejak ${employee.since}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Start
                )
            } else {
                projectCodePemeta.forEachIndexed { index, experience ->
                    /* Text(
                         text = "${index + 1}. ${experience.title}"
                     ) */
                    Row(
                        modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${ index + 1 }.",
                            modifier
                                .width(35.dp)
                                .padding(start = 8.dp, end = 8.dp),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.W600,
                            textAlign = TextAlign.End,
                        )
                        Text(
                            text = experience.title,
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.W600,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }

            // Pengalaman Kerja diluar Pemeta
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Pengalaman Kerja Profesional Lainnya",
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onErrorContainer,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            if (projectCodeOutside == null) {
                Text(
                    text = "Tidak ada data pengalaman diluar Pemeta",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Start
                )
            } else {
                projectCodeOutside.forEachIndexed { index, experience ->
                    /* Text(
                         text = "${index + 1}. ${experience.title}"
                     ) */
                    Row(
                        modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${ index + 1 }.",
                            modifier
                                .width(35.dp)
                                .padding(start = 8.dp, end = 8.dp),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.W600,
                            textAlign = TextAlign.End,
                        )
                        Text(
                            text = experience,
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.W600,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = PIXEL_9_PRO)
fun CurriculumVitaePemetaPreview(){
    PemetaCardTheme {
        val employeePreview = Team.teamManagement[1]
        CurriculumVitaePemeta(employeePreview)
    }
}