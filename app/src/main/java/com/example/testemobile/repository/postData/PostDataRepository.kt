package com.example.testemobile.repository.postData

interface PostDataRepository {

    suspend fun sendData(data: String): Boolean
}