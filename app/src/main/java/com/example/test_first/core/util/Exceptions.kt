package com.example.test_first.core.util

open class ExceptionTest : Exception()

class NetworkException : ExceptionTest()
class TimeoutException : ExceptionTest()
class ServerException(val errorCode: Int? = null) : ExceptionTest()
