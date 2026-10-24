package com.pemeta.pemetacard.ui.screen.client.list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.model.client.Client
import com.pemeta.pemetacard.model.client.DataPemetaClient
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun ListClient(
    modifier: Modifier = Modifier,
    navController: NavController,
    clients: List<Client>
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.padding(2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_more_vertical_square_svgrepo_com),
                contentDescription = stringResource(id = R.string.list_clients),
            )

            Text(
                text = stringResource(id = R.string.list_clients),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(2.dp),
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        clients.forEachIndexed { _, client ->
            ItemClientInfoCard(
                client = client,
                navController = navController
            )
            Spacer(modifier = Modifier.height(0.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListClientPreview(){
    PemetaCardTheme {

        val listClientDataPreview = DataPemetaClient.ClientList

        ListClient(
            clients = listClientDataPreview,
            navController = rememberNavController()
        )
    }
}