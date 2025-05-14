package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.games.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PartyBoxApp()
                }
            }
        }
    }
}

@Composable
fun PartyBoxApp() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "main_menu") {
        composable("main_menu") {
            MainMenuScreen(navController)
        }
        composable("truth_or_dare") {
            TruthOrDareGame(navController)
        }
        composable("bottle_spinner") {
            BottleSpinnerGame(navController)
        }
        composable("dice_game") {
            DiceGame(navController)
        }
        composable("word_challenge") {
            WordChallengeGame(navController)
        }
        composable("never_have_i_ever") {
            NeverHaveIEverGame(navController)
        }
    }
}

@Composable
fun MainMenuScreen(navController: androidx.navigation.NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "PARTY BOX",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 48.dp)
        )
        
        GameButton(text = "Action ou Vérité", onClick = { navController.navigate("truth_or_dare") })
        GameButton(text = "Jeu de la Bouteille", onClick = { navController.navigate("bottle_spinner") })
        GameButton(text = "Jeu de Dés", onClick = { navController.navigate("dice_game") })
        GameButton(text = "Défi de Mots", onClick = { navController.navigate("word_challenge") })
        GameButton(text = "Je n'ai jamais...", onClick = { navController.navigate("never_have_i_ever") })
    }
}

@Composable
fun GameButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(70.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
    }
}