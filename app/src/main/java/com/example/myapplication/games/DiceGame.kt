package com.example.myapplication.games

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun DiceGame(navController: NavController = androidx.navigation.compose.rememberNavController()) {
    var numDice by remember { mutableStateOf(2) }
    var isRolling by remember { mutableStateOf(false) }
    var diceValues by remember { mutableStateOf(List(3) { 1 }) }
    var diceRotations by remember { mutableStateOf(List(3) { 0f }) }
    
    val rotations = List(3) { remember { Animatable(0f) } }

    LaunchedEffect(isRolling) {
        if (isRolling) {
            // Générer des rotations aléatoires pour chaque dé
            val newRotations = List(3) { Random.nextFloat() * 360f }
            diceRotations = newRotations
            
            // Animer les dés
            rotations.forEachIndexed { index, animatable ->
                animatable.snapTo(0f)
                animatable.animateTo(
                    targetValue = 720f + newRotations[index],
                    animationSpec = tween(
                        durationMillis = 800,
                        easing = FastOutSlowInEasing
                    )
                )
            }
            
            // Générer de nouvelles valeurs de dés
            diceValues = List(3) { Random.nextInt(1, 7) }
            
            delay(800)
            isRolling = false
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
            text = "JEU DE DÉS",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        
        Text(
            text = if (isRolling) "Les dés roulent..." else "Total: ${diceValues.take(numDice).sum()}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            for (i in 0 until numDice) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(80.dp)
                        .rotate(rotations[i].value % 360)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = diceValues[i].toString(),
                        fontSize = 32.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Nombre de dés:", fontSize = 16.sp)
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = numDice == 1,
                    onClick = { numDice = 1 },
                    enabled = !isRolling
                )
                Text("1")
                
                Spacer(modifier = Modifier.width(8.dp))
                
                RadioButton(
                    selected = numDice == 2,
                    onClick = { numDice = 2 },
                    enabled = !isRolling
                )
                Text("2")
                
                Spacer(modifier = Modifier.width(8.dp))
                
                RadioButton(
                    selected = numDice == 3,
                    onClick = { numDice = 3 },
                    enabled = !isRolling
                )
                Text("3")
            }
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Button(
            onClick = { isRolling = true },
            enabled = !isRolling,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(60.dp)
        ) {
            Text("LANCER LES DÉS", fontSize = 18.sp)
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