package com.example.testemobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.testemobile.database.PurchaseEntity
import com.example.testemobile.repository.car.CarRepositoryImpl
import com.example.testemobile.repository.purchase.PurchaseRepositoryImpl
import com.example.testemobile.response.Resource
import com.example.testemobile.ui.state.HomeUiState
import com.example.testemobile.worker.PostWork
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class HomeScreenViewModel(
    private val carRepository: CarRepositoryImpl,
    private val purchaseRepository: PurchaseRepositoryImpl,
    private val workManager: WorkManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val _purchaseData = MutableStateFlow<PurchaseEntity?>(null)
    val purchaseData: StateFlow<PurchaseEntity?> = _purchaseData.asStateFlow()

    private val _statusUpdate = MutableStateFlow(false)
    val statusUpdate: StateFlow<Boolean> = _statusUpdate.asStateFlow()

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
            getDataPurchase()
        }
    }

    fun updatePurchase(purchaseEntity: PurchaseEntity) {
        viewModelScope.launch {
            purchaseData.value.let {
                if (it?.id == purchaseEntity.id) {
                    _statusUpdate.value = false
                } else {
                    it?.let {
                        purchaseRepository.deletePurchase(it)
                    }
                    purchaseRepository.insertPurchase(purchaseEntity)
                    _purchaseData.value = purchaseEntity
                    _statusUpdate.value = true
                }
            }

        }
    }

    fun createWorkManager(dataPost: PurchaseEntity) {
        workManager.cancelUniqueWork("upload_task")
        val workData = workDataOf("data_key" to dataPost.toString())
        val uploadWorkRequest = PeriodicWorkRequestBuilder<PostWork>(15, TimeUnit.MINUTES)
            .setInputData(workData)
            .build()
        workManager.enqueueUniquePeriodicWork(
            "upload_task",
            ExistingPeriodicWorkPolicy.REPLACE,
            uploadWorkRequest
        )
    }

    fun insertPurchase(purchaseEntity: PurchaseEntity) {
        viewModelScope.launch {
            purchaseRepository.insertPurchase(purchaseEntity)
            _purchaseData.value = purchaseRepository.getPurchase().firstOrNull()
        }
    }

    private fun getDataPurchase() {
        viewModelScope.launch {
            val purchase = purchaseRepository.getPurchase().firstOrNull()
            _purchaseData.value = purchase
            _purchaseData.value?.let { createWorkManager(it) }
        }
    }
}