package dev.lchang.countryappue.presentation.apifootball

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import kotlin.math.exp


@Composable
fun ApiFootballScreen(viewModel: ApiFootballViewModel = viewModel()){

    val countries by viewModel.countries.collectAsState()
    val selectedCountry by viewModel.selectedCountry.collectAsState()
    val teams by viewModel.teams.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
    ){
        Text(text = "Equipos de fútbol por país", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Box {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = selectedCountry?.name ?: "Selecciona un país")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }) {
                countries.forEach { country ->
                    DropdownMenuItem(
                        text = { Text(text = country.name) },
                        onClick = {
                            viewModel.onCountrySelected(country)
                            expanded = false
                        }
                    )
                }
            }
        }
            Spacer(modifier = Modifier.height(16.dp))

            if(isLoading){
                CircularProgressIndicator()
            }else if(error != null) {
                Text(text = error ?: "Error desconocido", color = MaterialTheme.colorScheme.error)
            }else {

                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(teams) { wrapper ->
                        Card(
                           modifier = Modifier
                                    .fillMaxSize()
                                    .padding(vertical = 4.dp)
                        ){
                            Row(modifier = Modifier.padding(16.dp)){
                                Image(
                                    painter = rememberAsyncImagePainter(model = wrapper.team.logo),
                                    contentDescription = wrapper.team.name,
                                    modifier = Modifier.size(64.dp),
                                    contentScale = ContentScale.Crop

                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                Column {
                                    Text(
                                        text = wrapper.team.name,
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                    Text(
                                        text = wrapper.team.country,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = wrapper.team.founded.toString(),
                                        style = MaterialTheme.typography.titleSmall
                                    )
                                }
                            }
                        }
                    }
                }
            }


        }
    }




