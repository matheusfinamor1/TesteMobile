package com.example.testemobile.ui.state

import com.example.testemobile.data.Car

data class HomeUiState(
    val cars: List<Car> = emptyList()
)
