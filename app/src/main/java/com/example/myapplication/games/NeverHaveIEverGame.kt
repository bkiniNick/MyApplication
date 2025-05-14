package com.example.myapplication.games

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun NeverHaveIEverGame(navController: NavController = androidx.navigation.compose.rememberNavController()) {
    val statements = listOf(
        "Je n'ai jamais menti à mes parents",
        "Je n'ai jamais mangé de la nourriture tombée par terre",
        "Je n'ai jamais conduit en excès de vitesse",
        "Je n'ai jamais triché à un examen",
        "Je n'ai jamais envoyé un message à la mauvaise personne",
        "Je n'ai jamais séché un cours",
        "Je n'ai jamais fait semblant de ne pas reconnaître quelqu'un",
        "Je n'ai jamais oublié un anniversaire important",
        "Je n'ai jamais chanté sous la douche",
        "Je n'ai jamais pleuré devant un film",
        "Je n'ai jamais menti sur mon âge",
        "Je n'ai jamais fait semblant d'aimer un cadeau",
        "Je n'ai jamais commandé plus de nourriture que je ne pouvais manger",
        "Je n'ai jamais regardé la fin d'un film avant de le commencer",
        "Je n'ai jamais fait semblant d'être malade pour éviter une obligation",
        "Je n'ai jamais fouillé dans les affaires de quelqu'un d'autre",
        "Je n'ai jamais porté les mêmes vêtements plusieurs jours de suite",
        "Je n'ai jamais utilisé le téléphone de quelqu'un sans permission",
        "Je n'ai jamais fait semblant de comprendre quelque chose que je ne comprenais pas",
        "Je n'ai jamais menti sur ce que j'ai fait le week-end",
        "Je n'ai jamais ignoré délibérément un appel téléphonique",
        "Je n'ai jamais gardé la monnaie quand on m'en a donné trop",
        "Je n'ai jamais prétendu être occupé(e) pour éviter quelqu'un",
        "Je n'ai jamais jugé quelqu'un sur son apparence",
        "Je n'ai jamais menti dans mon CV",
        "Je n'ai jamais fait semblant d'aimer une chanson pour impressionner quelqu'un",
        "Je n'ai jamais mangé tout un paquet de biscuits en une seule fois",
        "Je n'ai jamais pris un selfie plus de 5 fois pour en avoir un bon",
        "Je n'ai jamais fait semblant d'être au téléphone pour éviter de parler à quelqu'un",
        "Je n'ai jamais dansé seul(e) dans ma chambre",
        "Je n'ai jamais espionné mes voisins",
        "Je n'ai jamais fait un régime qui n'a duré qu'une journée",
        "Je n'ai jamais inventé une excuse pour quitter une fête tôt",
        "Je n'ai jamais fait semblant de connaître une chanson",
        "Je n'ai jamais lu le journal intime de quelqu'un",
        "Je n'ai jamais prétendu être malade pour avoir de l'attention",
        "Je n'ai jamais menti sur l'endroit où j'étais",
        "Je n'ai jamais caché quelque chose à ma famille",
        "Je n'ai jamais fait semblant d'être occupé(e) au travail",
        "Je n'ai jamais prétendu avoir lu un livre que je n'ai pas lu"
    )
    
    var currentStatement by remember { mutableStateOf("Appuyez sur NOUVEAU pour commencer") }
    var usedStatements by remember { mutableStateOf(mutableListOf<String>()) }
    
    fun getNewStatement(): String {
        val availableStatements = statements.filter { it !in usedStatements }
        return if (availableStatements.isNotEmpty()) {
            val newStatement = availableStatements.random()
            usedStatements.add(newStatement)
            newStatement
        } else {
            // Si toutes les déclarations ont été utilisées, on réinitialise
            usedStatements.clear()
            val newStatement = statements.random()
            usedStatements.add(newStatement)
            newStatement
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
            text = "JE N'AI JAMAIS...",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = currentStatement,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Comment jouer:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                Text(
                    text = "1. Lisez la déclaration à voix haute\n" +
                          "2. Tous les joueurs qui ONT fait ce qui est mentionné doivent boire une gorgée ou faire un gage\n" +
                          "3. Appuyez sur NOUVEAU pour continuer",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = { currentStatement = getNewStatement() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("NOUVEAU", fontSize = 18.sp)
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