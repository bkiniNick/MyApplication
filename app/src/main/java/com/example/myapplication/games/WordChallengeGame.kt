package com.example.myapplication.games

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun WordChallengeGame(navController: NavController = androidx.navigation.compose.rememberNavController()) {
    val themes = listOf(
        "Animaux", "Pays", "Villes", "Fruits", "Légumes", "Métiers", 
        "Sports", "Films", "Séries TV", "Marques", "Célébrités", 
        "Instruments de musique", "Moyens de transport", "Couleurs", 
        "Objets du quotidien", "Vêtements", "Boissons", "Plats", 
        "Sciences", "Technologie", "Nature", "Meubles", "Outils"
    )
    
    var currentTheme by remember { mutableStateOf("") }
    var timeLeft by remember { mutableStateOf(30) }
    var isGameRunning by remember { mutableStateOf(false) }
    var randomLetter by remember { mutableStateOf('A') }
    
    // Lettres les plus fréquentes en français
    val commonLetters = listOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'L', 'M', 'N', 'P', 'R', 'S', 'T')
    
    LaunchedEffect(isGameRunning) {
        if (isGameRunning) {
            currentTheme = themes.random()
            randomLetter = commonLetters.random()
            timeLeft = 30
            
            while (timeLeft > 0 && isGameRunning) {
                delay(1000)
                timeLeft--
            }
            
            if (timeLeft == 0) {
                isGameRunning = false
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "DÉFI DE MOTS",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        
        if (isGameRunning) {
            // Afficher le minuteur
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(
                        color = if (timeLeft > 10) MaterialTheme.colorScheme.primaryContainer 
                               else MaterialTheme.colorScheme.errorContainer
                    )
            ) {
                Text(
                    text = timeLeft.toString(),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (timeLeft > 10) MaterialTheme.colorScheme.onPrimaryContainer
                           else MaterialTheme.colorScheme.onErrorContainer
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Thème:",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Text(
                        text = currentTheme,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "Lettre:",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Text(
                        text = randomLetter.toString(),
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Trouvez un mot sur ce thème commençant par cette lettre!",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Button(
                onClick = { isGameRunning = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("ARRÊTER", fontSize = 18.sp)
            }
        } else {
            // Écran d'instructions
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Comment jouer:",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    
                    Text(
                        text = "1. Appuyez sur DÉMARRER\n" +
                              "2. Un thème et une lettre seront tirés au sort\n" +
                              "3. Vous avez 30 secondes pour trouver un mot sur ce thème commençant par cette lettre\n" +
                              "4. Dites votre réponse à voix haute\n" +
                              "5. Les autres joueurs valident ou contestent votre réponse",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Button(
                onClick = { isGameRunning = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(60.dp)
            ) {
                Text("DÉMARRER", fontSize = 18.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            enabled = !isGameRunning
        ) {
            Text("Retour au menu principal")
        }
    }
} 