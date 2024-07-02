package com.example.testemobile.repository.car

import com.example.testemobile.data.Cars
import com.example.testemobile.response.Resource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

class CarRepositoryImpl(
    private val httpClient: HttpClient
) : CarRepository {
    private var status: Resource<Cars> = Resource.Loading()
    override suspend fun getCars(): Resource<Cars> {
        try {
            val response: HttpResponse =
                httpClient.get(BASE_URL)
            when (response.status.value) {
                in 200..299 -> {
                    val responseJson = response.bodyAsText()
                    val responseData = Json.decodeFromString<Cars>(responseJson)
                    status = Resource.Success(responseData)
                }

                in 300..399 -> {
                    status = Resource.Error(ERROR_NOT_FOUND)
                }

                in 400..499 -> {
                    status = Resource.Error(ERROR_CLIENT)
                }

                in 500..599 -> {
                    status = Resource.Error(ERROR_SERVICE)
                }
            }

        } catch (e: Exception) {
            status = Resource.Error(e.message.toString())
        }
        return status
    }

    companion object{
        private const val BASE_URL = "https://wswork.com.br/cars.json"
        private const val ERROR_NOT_FOUND = "Client not found"
        private const val ERROR_CLIENT = "Error client"
        private const val ERROR_SERVICE = "Error service"
    }
}