package com.example.kilohealth.networkconfig


import android.util.Log
import coil3.network.HttpException
import java.io.IOException

sealed interface ErrorType{
    sealed interface Api:ErrorType{
        data object NotFound:Api
        data object Server:Api
        data object ServiceUnavailable: Api
        data object Network: Api
    }
    data object Unknown: ErrorType
    fun errorMessage(): String{
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
        Log.d("NEtworkErr", "apiHandler:${e.message}")
        XResource.Error(ErrorType.Api.Network)
    } catch (e: Exception) {
        Log.d("NEtworkErr", "apiHandler:${e.message}")
        XResource.Error(ErrorType.Unknown)
    }
}