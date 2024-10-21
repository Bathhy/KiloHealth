package com.example.kilohealth.networkconfig

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
    internal fun provideApiService(retrifit: Retrofit): ApiService {
        return retrifit.create(ApiService::class.java)
    }
}

object RetrofitService {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val headerInterceptor =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request()
                        .newBuilder()
                        .also {
                            it.addHeader("X-User-ID", "669e142544e1c41ced9a737f")
                        }.build()
                )

            }
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(NetworkConfigEndPoint.BASE_URL)
            .client(client)
            .client(headerInterceptor.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}