package com.example.kilohealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.kilohealth.navigation.RootNavGraph
import com.example.kilohealth.ui.theme.KiloHealthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KiloHealthTheme {
                val navControl = rememberNavController()
               Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

//                  SplashGraph(navHostController = navControl)
                   RootNavGraph(navHostController = navControl)
                }
            }
        }
    }
}

