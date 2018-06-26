package com.ciandt.androidreference.entity.error

sealed class ApiErrorType(var actionCode: Int? = null) {
    companion object {
        const val type: String = ""
    }
}

class GeneralError : ApiErrorType() {
    companion object {
        const val type: String = "ERROR"
    }
}

class Attention : ApiErrorType() {
    companion object {
        const val type: String = "ATENCAO"
    }
}

class ConfirmationPending : ApiErrorType() {
    companion object {
        const val type: String = "CONFIRMATION_PENDING"
    }
}

class UserBLocked : ApiErrorType() {
    companion object {
        const val type: String = "USER_BLOCKED"
    }
}

class TokenExpired : ApiErrorType() {
    companion object {
        const val type: String = "TOKEN_EXPIRED"
    }
}