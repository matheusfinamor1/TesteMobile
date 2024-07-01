package com.example.testemobile.repository.postData

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.isSuccess

class PostDataRepositoryImpl(
    private val httpClient: HttpClient,
) : PostDataRepository {
    override suspend fun sendData(data: String): Boolean {
        val response = httpClient.post("http://127.0.0.1:8080/receive") {
            setBody(data)
        }
        return response.status.isSuccess()
    }
}