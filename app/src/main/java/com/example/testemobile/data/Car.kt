package com.example.testemobile.data

data class Car(
    val id: Int,
    val timestampCadastro: Long,
    val modeloId: String,
    val ano: Int,
    val combustivel: String,
    val numPortas: Int,
    val cor: String,
    val nomeModelo: String,
    val valor: Double
)
