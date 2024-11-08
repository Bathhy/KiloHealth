package com.example.kilohealth.networkconfig


import android.util.Log
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

sealed class XResource<T> {
    data class Success<T>(val data: T) : XResource<T>()
    data class Error<T>(val error: ErrorType) : XResource<T>()
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
): XResource<T> {
    return try {
        XResource.Success(apiCall())
    } catch (e:HttpException) {
        Log.d("errorHttpExcep", "apiHandler:${e.response.code}")
        when (e.response.code) {
            404 -> XResource.Error(ErrorType.Api.NotFound)

            503 -> XResource.Error(ErrorType.Api.ServiceUnavailable)
            else -> XResource.Error(ErrorType.Api.Server)
        }
    } catch (e: IOException) {
        Log.d("IOExcep", "apiHandler:${e.message}")
        XResource.Error(ErrorType.Api.Network)
    } catch (e: Exception) {
        Log.d("Except", "apiHandler:${e.message}")
        XResource.Error(ErrorType.Unknown)
    }
}