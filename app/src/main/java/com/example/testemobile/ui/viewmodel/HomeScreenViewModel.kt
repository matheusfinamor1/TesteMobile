package com.example.testemobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testemobile.repository.CarRepositoryImpl
import com.example.testemobile.response.Resource
import com.example.testemobile.ui.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val carRepository: CarRepositoryImpl
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            when (val cars = carRepository.getCars()) {
                is Resource.Success -> {
                    val carsData = cars.data?.cars
                    carsData?.let {
                        _uiState.update { currentState ->
                            currentState.copy(
                                cars = it
                            )
                        }
                    }
                }

                is Resource.Error -> {}
                is Resource.Loading -> {}
            }
        }
    }
}