package com.example.testemobile.repository

import com.example.testemobile.data.Cars
import com.example.testemobile.response.Resource

interface CarRepository {
    suspend fun getCars(): Resource<Cars>
}