package com.example.kilohealth.module

import com.example.kilohealth.networkconfig.HealthNetworkModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(includes = [HealthNetworkModule::class])
@ComponentScan("com.example.kilohealth")
class KiloHealthModule

