package com.example.myapplication.games

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import kotlin.random.Random

@Composable
fun BottleSpinnerGame(navController: NavController = androidx.navigation.compose.rememberNavController()) {
    var isSpinning by remember { mutableStateOf(false) }
    var finalRotation by remember { mutableStateOf(0f) }
    val rotation = remember { Animatable(0f) }
    val bottleImage: Painter = painterResource(id = R.drawable.bottle)
    
    LaunchedEffect(isSpinning) {
        if (isSpinning) {
            // Générer un angle aléatoire entre 0 et 360 × (3 à 5) degrés
            finalRotation = Random.nextFloat() * 360f * (3f + Random.nextFloat() * 2f)
            
            // Animation de rotation avec rebond à la fin
            rotation.animateTo(
                targetValue = finalRotation,
                animationSpec = tween(
                    durationMillis = 2000,
                    easing = FastOutSlowInEasing
                )
            )
            
            isSpinning = false
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
            text = "JEU DE LA BOUTEILLE",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        
        Text(
            text = if (isSpinning) "La bouteille tourne..." else "Appuyez sur la bouteille pour la faire tourner",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Box(
            modifier = Modifier
                .size(300.dp)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            // Cercle des joueurs (optionnel)
            Surface(
                modifier = Modifier.size(280.dp),
                shape = androidx.compose.foundation.shape.CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
            ) {}
            
            // Image de la bouteille qui tourne
            Image(
                painter = bottleImage,
                contentDescription = "Bouteille",
                modifier = Modifier
                    .fillMaxSize(0.8f)
                    .rotate(rotation.value % 360)
                    .clickable(
                        enabled = !isSpinning,
                        onClick = { isSpinning = true }
                    )
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = { isSpinning = true },
            enabled = !isSpinning,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(60.dp)
        ) {
            Text("TOURNER LA BOUTEILLE", fontSize = 18.sp)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text("Retour au menu principal")
        }
    }
} 