package com.example.test_first.core.util.exception

import android.content.Context
import com.example.test_first.R
import com.example.test_first.core.util.NetworkException
import com.example.test_first.core.util.ServerException
import com.example.test_first.core.util.TimeoutException

class UIExceptionMapper {
    fun titleMapper(context: Context, throwable: Throwable?): String {
        return when (throwable) {
            is NetworkException -> context.resources.getString(R.string.error_network)
            is ServerException -> context.resources.getString(R.string.error_server)
            is TimeoutException -> context.resources.getString(R.string.error_smt_go_wrong)
            else -> context.resources.getString(R.string.error_smt_go_wrong)
        }
    }

    fun subtitleMapper(context: Context, throwable: Throwable?): String {
        return when (throwable) {
            is NetworkException -> context.resources.getString(R.string.error_network_subtitle)
            is ServerException -> context.resources.getString(R.string.error_server_subtitle)
            is TimeoutException -> context.resources.getString(R.string.error_smt_go_wrong_subtitle)
            else -> context.resources.getString(R.string.error_smt_go_wrong_subtitle)
        }
    }
}
