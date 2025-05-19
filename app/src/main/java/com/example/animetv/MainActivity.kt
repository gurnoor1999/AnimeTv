package com.example.animetv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animetv.ui.screens.DetailScreen
import com.example.animetv.ui.screens.HomeScreen
import com.example.animetv.ui.theme.AnimeTVTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimeTVTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                   NavHost(navController = navController, startDestination = "home", modifier = Modifier.padding(paddingValues)) {
                       composable("home"){
                           HomeScreen(navController)
                       }
                       composable("detail/{animeId}"){ backStackEntry ->
                           val animeId = backStackEntry.arguments?.getString("animeId")?.toInt() ?: 0
                           DetailScreen(animeId)
                       }
                   }
                }
            }
        }
    }
}
