package com.example.kilohealth.networkconfig


import coil3.network.HttpException
import java.io.IOException

sealed class ErrorType {

    sealed class Api : ErrorType() {
        object Network : Api()
        object ServiceUnavailable : Api()
        object NotFound : Api()
        object Server : Api()
    }

    object Unknown : ErrorType()

    override fun toString(): String {
        return when (this) {
            is Api.Network -> "Network Error"
            is Api.NotFound -> "Not Found"
            is Api.Server -> "Server Error"
            is Api.ServiceUnavailable -> "Service Unavailable"
            Unknown -> "Unknown Error"
        }
    }
}

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val error: ErrorType) : Resource<T>()
}

object MessageError {
    const val NETWORK_ERROR = "No Internet Connection.Please Check connection"
    const val ERROR_NOT_FOUND = "Error 404 !! Not found"
    const val SERVER_ERROR = "Server is error. Please try again later"
    const val SERVICE_UNAVAILABLE = "Service is in maintenance"
    const val UNKNOWN_ERROR = "Unexpected error! please restart app"
}

suspend fun <T> apiHandler(
    apiCall: suspend () -> T
): Resource<T> {
    return try {
        Resource.Success(apiCall())
    } catch (e:HttpException) {
        when (e.response.code) {
            404 -> Resource.Error(ErrorType.Api.NotFound)
            503 -> Resource.Error(ErrorType.Api.ServiceUnavailable)
            else -> Resource.Error(ErrorType.Api.Server)
        }
    } catch (e: IOException) {
        Resource.Error(ErrorType.Api.Network)
    } catch (e: Exception) {
        Resource.Error(ErrorType.Unknown)
    }
}