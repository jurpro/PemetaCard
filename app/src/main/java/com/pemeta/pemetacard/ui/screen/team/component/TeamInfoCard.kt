package com.pemeta.pemetacard.ui.screen.team.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.team.Employee
import com.pemeta.pemetacard.model.team.Team
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun TeamInfoCard(
    employee: Employee,
    navController: NavController,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current

    Column(
        modifier
            .width(150.dp)
            .clickable { navController.navigate(ScreenMenu.DetailTeam.createRoute(teamId = employee.id)) },
          //  .clickable{Toast.makeText(context, "Thank you", Toast.LENGTH_LONG).show()},
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier
                .size(150.dp)
                .padding(2.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(employee.photo),
                contentDescription = null,
                modifier.size(300.dp),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = employee.name,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            textAlign = TextAlign.Center
        )

        Text(
            text = employee.position,
            fontSize = 12.sp,
            lineHeight = 12.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
@Preview(showBackground = true, device = PIXEL_9_PRO)
fun TeamInfoCardPreview(){
    PemetaCardTheme {
        val employeePreview = Team.teamManagement[4]
        TeamInfoCard(
            employee = employeePreview,
            navController = rememberNavController()
        )
    }
}