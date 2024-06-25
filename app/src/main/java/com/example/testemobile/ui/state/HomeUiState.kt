package com.example.testemobile.ui.state

data class HomeUiState(
    val id: Int = 0,
    val timestampCadastro: Long = 0,
    val modeloId: String = "",
    val ano: Int = 0,
    val combustivel: String = "",
    val numPortas: Int = 0,
    val cor: String = "",
    val nomeModelo: String = "",
    val valor: Double = 0.0
)
