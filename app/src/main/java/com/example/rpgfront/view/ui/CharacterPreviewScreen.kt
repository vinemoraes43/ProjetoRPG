package com.example.rpgfront.view.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.rpgfront.model.atributos.*
import com.example.rpgfront.model.personagem.Personagem

@Composable
fun CharacterPreviewScreen(
    personagem: Personagem,
    onBack: () -> Unit,
    onConfirm: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text("Pré-visualização do Personagem", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Nome: ${personagem.nome}")
        Text("Nível: ${personagem.nivel}")

        Spacer(modifier = Modifier.height(12.dp))

        Text("Raça: ${personagem.raca.nome}")
        Text("Bônus de Raça: ${personagem.raca.bonusDescricao()}")

        Spacer(modifier = Modifier.height(12.dp))

        Text("Classe: ${personagem.classe.nome}")
        Text("Habilidades iniciais:")
        personagem.classe.habilidades.forEach { habilidade ->
            Text("- $habilidade")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text("Atributos:")

        Atributo.values().forEach { atributo ->
            val base = personagem.atributosBase[atributo] ?: 0
            val final = personagem.atributos[atributo] ?: base
            val bonus = final - base

            if (bonus != 0) {
                Text(
                    buildAnnotatedString {
                        append("${atributo.name}: $base ")
                        withStyle(
                            style = SpanStyle(color = Color(0xFF4CAF50)) // Verde Material
                        ) {
                            append("(+$bonus) ")
                        }
                        append("= $final")
                    }
                )
            } else {
                Text("${atributo.name}: $final")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(onClick = onBack) { Text("Voltar") }
            Button(onClick = onConfirm) { Text("Confirmar") }
        }
    }
}
