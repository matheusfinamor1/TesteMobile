@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.testemobile.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
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
    viewModel.getDataPurchase()
    val purchaseData by viewModel.purchaseData.collectAsState()
    purchaseData?.let {
        Log.d("Response", "HomeScreen: ${it}")
    }
}

@Composable
fun ListCars(
    listCars: HomeUiState,
    viewModelHomeScreen: HomeScreenViewModel
) {
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
    val openDialogEmailUser = remember { mutableStateOf(false) }
    val openDialogConfirmPurchase = remember { mutableStateOf(false) }
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
                            val purchaseData = viewModelHomeScreen.purchaseData.value
                            when (purchaseData) {
                                null -> {
                                    openDialogEmailUser.value = true
                                }

                                else -> {
                                    openDialogConfirmPurchase.value = true
                                    viewModelHomeScreen.updatePurchase(car.toPurchaseEntity(email = purchaseData.emailComprador))
                                }
                            }
                        }
                    ) {
                        Text(text = "EU QUERO")
                    }
                    when (openDialogEmailUser.value) {
                        true -> DialogEmailUser(
                            openAlertDialog = openDialogEmailUser,
                            car = car,
                            viewModelHomeScreen = viewModelHomeScreen
                        )

                        false -> {}
                    }
                    when (openDialogConfirmPurchase.value) {
                        true -> DialogConfirmPurchase(
                            title = "Finalizado",
                            msg = "Sua compra foi finalizada com sucesso!",
                            actionConfirm = { openDialogConfirmPurchase.value = false },
                            showDialog = openDialogConfirmPurchase
                        )

                        false -> {}
                    }
                }
            }
        }
    }
}

@Composable
fun DialogEmailUser(
    openAlertDialog: MutableState<Boolean>,
    car: Car,
    viewModelHomeScreen: HomeScreenViewModel
) {
    val text = remember {
        mutableStateOf("")
    }
    Box(
        Modifier.fillMaxSize()
    ) {
        if (openAlertDialog.value) {
            Dialog(onDismissRequest = { openAlertDialog.value = false }) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8))
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(Color.LightGray)
                ) {
                    TextField(
                        value = text.value,
                        onValueChange = { text.value = it },
                        label = { Text("Digite seu email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                    )

                    TextButton(
                        onClick = {
                            Log.d(
                                "Response", "DialogEmailUser: inserindo usuario: " +
                                        "${
                                            PurchaseEntity(
                                                id = car.id,
                                                emailComprador = text.value,
                                                timestampCadastro = car.timestampCadastro,
                                                modeloId = car.modeloId,
                                                ano = car.ano,
                                                combustivel = car.combustivel,
                                                numPortas = car.numPortas,
                                                cor = car.cor,
                                                nomeModelo = car.nomeModelo,
                                                valor = car.valor
                                            )
                                        }"
                            )
                            viewModelHomeScreen.insertPurchase(
                                PurchaseEntity(
                                    id = car.id,
                                    emailComprador = text.value,
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
                            openAlertDialog.value = false
                        },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirmar")
                    }
                }
            }
        }
    }
}

@Composable
fun DialogConfirmPurchase(
    title: String,
    msg: String,
    actionConfirm: () -> Unit,
    showDialog: MutableState<Boolean>
) {
    if (showDialog.value) {
        Dialog(
            onDismissRequest = { showDialog.value = false },
            content = {
                AlertDialog(
                    onDismissRequest = { showDialog.value = false },
                    title = { Text(text = title) },
                    text = { Text(text = msg) },
                    confirmButton = {
                        TextButton(onClick = {
                            showDialog.value = false
                            actionConfirm()
                        }) {
                            Text("Confirmar")
                        }
                    }
                )
            }
        )
    }
}