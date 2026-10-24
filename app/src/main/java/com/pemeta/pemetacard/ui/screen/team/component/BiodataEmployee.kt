package com.pemeta.pemetacard.ui.screen.team.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pemeta.pemetacard.model.team.Employee
import com.pemeta.pemetacard.model.team.Team
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun BiodataEmployee(
    employee: Employee,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp)
            .clip(RoundedCornerShape(12.dp))
            .padding(8.dp)
        // .clickable { Toast.makeText(context, R.string.clickable_dev_detail, Toast.LENGTH_LONG).show() },
    ) {
        Column(
            modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = employee.name,
                modifier.padding(top = 4.dp),
                fontWeight = FontWeight.Black
            )
            Text(
                text = employee.position,
                modifier.padding(top = 4.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = PIXEL_9_PRO)
fun BiodataEmployeePreview(){
    PemetaCardTheme {
        val employeePreview = Team.teamManagement[0]
        BiodataEmployee(
            employee = employeePreview
        )
    }
}