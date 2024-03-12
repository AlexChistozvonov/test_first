package com.example.test_first.core.util.extantion

import android.util.Log
import com.example.test_first.core.util.ExceptionTest
import com.example.test_first.core.util.ServerException
import com.example.test_first.core.util.TimeoutException
import java.net.SocketTimeoutException
import javax.inject.Inject

suspend fun <T : Any> runLoading(
    mapper: ErrorMapper,
    block: suspend () -> T
): LoadingResult<T> {
    return try {
        LoadingResult.Success(block())
    } catch (error: Throwable) {
        Log.e("error", "runLoading error: $error")
        LoadingResult.Error(mapper.mapException(error))
    }
}

sealed class LoadingResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : LoadingResult<T>()
    data class Error(val exception: Exception) : LoadingResult<Nothing>()
}

class ErrorMapper @Inject constructor() {

    fun mapException(throwable: Throwable): ExceptionTest {
        return when (throwable) {
            is Exception -> invoke(throwable)
            else -> ServerException()
        }
    }

    operator fun invoke(exception: Exception): ExceptionTest {
        return when (exception) {
            is SocketTimeoutException -> TimeoutException()
            else -> ServerException()
        }
    }
}
