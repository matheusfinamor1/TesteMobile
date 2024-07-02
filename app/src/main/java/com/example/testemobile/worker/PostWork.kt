package com.example.testemobile.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.testemobile.repository.postData.PostDataRepositoryImpl

class PostWork(
    context: Context,
    workerParameters: WorkerParameters,
    private val postDataRepository: PostDataRepositoryImpl
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        val data = inputData.getString("data_key") ?: ""
        val response = postDataRepository.sendData(data)
        return if (response) {
            Result.success()
        } else {
            Result.failure()
        }
    }
}
