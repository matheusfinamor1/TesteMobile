package com.example.testemobile.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Car(
    val id: Int,
    @SerialName("timestamp_cadastro") val timestampCadastro: Long,
    @SerialName("modelo_id") val modeloId: Int,
    val ano: Int,
    val combustivel: String,
    @SerialName("num_portas") val numPortas: Int,
    val cor: String,
    @SerialName("nome_modelo") val nomeModelo: String,
    val valor: Double
)
