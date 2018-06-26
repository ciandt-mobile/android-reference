package com.ciandt.androidreference.entity.error

sealed class ErrorType
class Http(val statusCode: Int, val errorBody: String?, var apiErrorType: ApiErrorType? = null) :
    ErrorType()

object Network : ErrorType()
object Conversion : ErrorType()
object NullBody : ErrorType()
object Unexpected : ErrorType()