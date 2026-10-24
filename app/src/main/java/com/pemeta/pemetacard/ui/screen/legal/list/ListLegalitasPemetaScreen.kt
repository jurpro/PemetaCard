package com.pemeta.pemetacard.ui.screen.legal.list

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.data.Injection
import com.pemeta.pemetacard.extention.multifunction.CopyrightPemeta
import com.pemeta.pemetacard.model.lagilitas.data.DataLegalitas
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.ViewModelFactory
import com.pemeta.pemetacard.ui.screen.experience.list.component.TopBar
import com.pemeta.pemetacard.ui.screen.info.contact.ContactUs
import com.pemeta.pemetacard.ui.screen.legal.list.component.LegalitasMenuGrid
import com.pemeta.pemetacard.ui.screen.legal.list.component.ListLegalitasViewModel
import com.pemeta.pemetacard.ui.screen.maps.MapsOfficePemeta
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ListLegalitasPemetaScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    modifier: Modifier = Modifier,
    listLegalitasViewModel: ListLegalitasViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
){
    Column (
        modifier = modifier
            .fillMaxSize()
           // .consumeWindowInsets(paddingValues)
          //  .background(MaterialTheme.colorScheme.primaryContainer)
    ){
        TopBar(navController = navController)

        Spacer(modifier = modifier.padding(top = 16.dp))

        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_more_vertical_square_svgrepo_com),
                contentDescription = stringResource(id = R.string.list_legalitas_more),
            )

            Text(
                text = stringResource(id = R.string.list_legalitas),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(2.dp),
            )
        }

        LegalitasMenuGrid(
            listLegalitas = DataLegalitas.LegalitasList,
            navController = navController,
            //  paddingValues =paddingValues,
            listLegalitasViewModel = listLegalitasViewModel
        )

        Spacer(modifier.padding(top = 12.dp))

      /*  item {
            // Alamat Pemeta
            MapsOfficePemeta(
                modifier = modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(R.string.company_address),
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = stringResource(R.string.company_phone),
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                // modifier = Modifier.padding(8.dp)
            )
            Text(
                text = stringResource(R.string.company_email),
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                // modifier = Modifier.padding(8.dp)
            )
            Text(
                text = stringResource(R.string.company_inkindo),
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                // modifier = Modifier.padding(8.dp)
            )
        } */




        // ContactUs()

        Button(
            onClick = {
                navController.navigate(ScreenMenu.Contact.route)
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Hubungi Kami".uppercase(),
                modifier
                    .padding(bottom = 4.dp, top = 4.dp)
            )
        }

        CopyrightPemeta()
        Spacer(modifier.padding(
            top = 12.dp,
            bottom = paddingValues.calculateBottomPadding()
        ))
    }
}

@Preview(showBackground = true)
@Composable
fun LegalitasPemetaScreenPreview(){
    PemetaCardTheme {
        ListLegalitasPemetaScreen(
            paddingValues = PaddingValues(),
            navController = rememberNavController()
        )
    }
}