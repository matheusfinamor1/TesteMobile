package com.example.testemobile.repository.postData

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.isSuccess

class PostDataRepositoryImpl(
    private val httpClient: HttpClient,
) : PostDataRepository {
    override suspend fun sendData(data: String): Boolean {

        try {
            val response = httpClient.post(BASE_POST_URL) {
                setBody(data)
            }
            return response.status.isSuccess()
        } catch (e: ClientRequestException) {
            Log.e("Error", "Erro de solicitação: ${e.response.status}")
            return false
        } catch (e: Exception) {
            Log.e("Error", "Erro generico: ${e.message}")
            return false
        }

    }

    companion object {
        private const val BASE_POST_URL = "https://www.wswork.com.br/cars/leads"
    }
}