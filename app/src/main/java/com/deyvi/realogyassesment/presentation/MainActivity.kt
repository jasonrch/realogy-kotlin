package com.deyvi.realogyassesment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.deyvi.realogyassesment.presentation.characters_list.CharactersListScreen
import com.deyvi.realogyassesment.presentation.ui.theme.RealogyAssesmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RealogyAssesmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ){
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.CharactersListScreen.route
                    ) {
                        composable(
                            route = Screens.CharactersListScreen.route
                        ) {
                            CharactersListScreen(navController)
                        }
                        composable(
                            route = Screens.CharacterDetailScreen.route
                        ) {
                        }
                    }
                }
            }
        }
    }
}
