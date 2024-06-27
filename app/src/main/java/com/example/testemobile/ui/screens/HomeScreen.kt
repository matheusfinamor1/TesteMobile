package com.example.testemobile.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testemobile.data.Car
import com.example.testemobile.database.PurchaseEntity
import com.example.testemobile.ui.state.HomeUiState
import com.example.testemobile.ui.viewmodel.HomeScreenViewModel

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    viewModel: HomeScreenViewModel
) {
    ListCars(listCars = uiState, viewModelHomeScreen = viewModel)
}

@Composable
fun ListCars(listCars: HomeUiState, viewModelHomeScreen: HomeScreenViewModel) {
    LazyColumn {
        items(listCars.cars) { car ->
            CarItem(car, viewModelHomeScreen)
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun CarItem(car: Car, viewModelHomeScreen: HomeScreenViewModel) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clickable { expanded = !expanded }
    ) {
        Card {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Text(text = "Nome: ")
                        Text(text = car.nomeModelo)
                    }
                    Row {
                        Text(text = "Ano: ")
                        Text(text = car.ano.toString())
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Text(text = "Combustível: ")
                        Text(text = car.combustivel)
                    }
                    Row {
                        Text(text = "Portas: ")
                        Text(text = car.numPortas.toString())
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        Text(text = "Cor: ")
                        Text(text = car.cor)
                    }
                    Row {
                        Text(text = "Valor: R$")
                        Text(text = String.format("%.3f", car.valor))
                    }
                }
                if (expanded) {
                    Button(
                        onClick = {
                            viewModelHomeScreen.insertPurchase(
                                PurchaseEntity(
                                    id = car.id,
                                    emailComprador = "",
                                    timestampCadastro = car.timestampCadastro,
                                    modeloId = car.modeloId,
                                    ano = car.ano,
                                    combustivel = car.combustivel,
                                    numPortas = car.numPortas,
                                    cor = car.cor,
                                    nomeModelo = car.nomeModelo,
                                    valor = car.valor
                                )
                            )
                        }
                    ) {
                        Text(text = "EU QUERO")
                    }
                }
            }
        }
    }

}