package com.example.data.base

import retrofit2.Response
import java.util.concurrent.CancellationException

abstract class BaseDataSourceHandler {

    suspend fun <T> invokeResponseRequest(
        request: suspend () -> Response<T>,
    ): Result<T> {
        return try {
            val response = request()
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Throwable(response.message()))
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(Throwable(request().message()))
        }
    }
}