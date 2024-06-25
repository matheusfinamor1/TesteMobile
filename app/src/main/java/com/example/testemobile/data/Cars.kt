package com.example.testemobile.data

import kotlinx.serialization.Serializable

@Serializable
data class Cars(
    val cars: List<Car>
)
