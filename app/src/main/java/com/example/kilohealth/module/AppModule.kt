package com.example.kilohealth.module

import com.example.kilohealth.feature.feature_home.data.local.HomeLocalDataSource
import com.example.kilohealth.feature.feature_home.data.local.HomeLocalDataSourceImpl
import com.example.kilohealth.localconfig.LocalDatabase
import com.example.kilohealth.localconfig.provideLocalDb
import com.example.kilohealth.networkconfig.HealthNetworkModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.dsl.module

@Module(includes = [HealthNetworkModule::class])
@ComponentScan("com.example.kilohealth")
class KiloHealthModule

val localModule = module {
    single {
        provideLocalDb(get())
    }
    factory { get<LocalDatabase>().dao }
    single <HomeLocalDataSource>{
        HomeLocalDataSourceImpl(get())
    }
}