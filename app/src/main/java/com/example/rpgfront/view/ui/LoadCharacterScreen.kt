package com.example.rpgfront.view.ui

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rpgfront.data.repository.PersonagemRepository
import com.example.rpgfront.model.personagem.Personagem
import kotlinx.coroutines.launch

@Composable
fun LoadCharacterScreen(
    context: Context,
    onBack: () -> Unit,
    onCharacterSelected: (Personagem) -> Unit
) {
    val repository = remember { PersonagemRepository(context) }
    val scope = rememberCoroutineScope()
    var personagens by remember { mutableStateOf<List<Personagem>>(emptyList()) }

    // Carrega os personagens salvos assim que a tela abre
    LaunchedEffect(Unit) {
        scope.launch {
            personagens = repository.listarTodosPersonagens()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Carregar Personagem", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        if (personagens.isEmpty()) {
            Text("Nenhum personagem salvo encontrado.")
        } else {
            personagens.forEach { personagem ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { onCharacterSelected(personagem) }
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Nome: ${personagem.nome}")
                        Text("Raça: ${personagem.raca.nome}")
                        Text("Classe: ${personagem.classe.nome}")
                        Text("Nível: ${personagem.nivel}")
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        OutlinedButton(onClick = onBack) {
            Text("Voltar")
        }
    }
}