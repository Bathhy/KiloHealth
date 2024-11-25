package com.example.kilohealth.networkconfig

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class HealthNetworkModule {

    @Single
    internal fun provideRetrofit(): Retrofit {
        return RetrofitService.retrofit
    }

    @Single
    internal fun provideApiService(myRetrofit: Retrofit): ApiService {
        return myRetrofit.create(ApiService::class.java)
    }


}

object RetrofitService {

    private val headerInterceptor = Interceptor { chain ->
        chain.proceed(
            chain.request()
                .newBuilder()
                .also {
                    it.addHeader("X-User-ID", "669e142544e1c41ced9a737f")
                }.build()
        )
    }
    private val urlInterceptor = HttpLoggingInterceptor { message ->
        Log.d(
            "BaseURL",
            "log:$message"
        )
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(urlInterceptor)
        .addInterceptor(headerInterceptor)
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(NetworkConfigEndPoint.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}