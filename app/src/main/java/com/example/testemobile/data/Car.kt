package com.example.testemobile.data

import com.example.testemobile.database.PurchaseEntity
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
){
    fun toPurchaseEntity(email: String): PurchaseEntity {
        return PurchaseEntity(
            id = this.id,
            emailComprador = email,
            timestampCadastro = this.timestampCadastro,
            modeloId = this.modeloId,
            ano = this.ano,
            combustivel = this.combustivel,
            numPortas = this.numPortas,
            cor = this.cor,
            nomeModelo = this.nomeModelo,
            valor = this.valor
        )
    }
}
