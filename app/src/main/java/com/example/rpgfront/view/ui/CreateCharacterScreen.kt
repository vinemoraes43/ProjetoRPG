package com.example.rpgfront.view.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rpgfront.controller.PersonagemControllerAndroid
import com.example.rpgfront.model.atributos.Atributo
import com.example.rpgfront.model.personagem.Personagem

@Composable
fun CreateCharacterScreen(
    controller: PersonagemControllerAndroid,
    onBack: () -> Unit,
    onCreated: (Personagem) -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var raca by remember { mutableStateOf("Humano") }
    var classe by remember { mutableStateOf("Guerreiro") }
    var estilo by remember { mutableStateOf("Clássico") }
    var atributos by remember { mutableStateOf<Map<Atributo, Int>>(emptyMap()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Criar Personagem", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        // Nome
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome do Personagem") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        // Raça
        Text("Raça")
        DropdownMenuBox(
            options = controller.getRacas(),
            selected = raca,
            onSelected = { raca = it }
        )

        Spacer(Modifier.height(12.dp))

        // Classe
        Text("Classe")
        DropdownMenuBox(
            options = controller.getClasses(),
            selected = classe,
            onSelected = { classe = it }
        )

        Spacer(Modifier.height(12.dp))

        // Estilo de Atributos
        Text("Estilo de Atributos")
        DropdownMenuBox(
            options = listOf("Clássico", "Heróico", "Aventureiro"),
            selected = estilo,
            onSelected = { estilo = it }
        )

        Spacer(Modifier.height(16.dp))

        // Botão Gerar Atributos
        Button(onClick = {
            atributos = controller.gerarAtributos(estilo.lowercase())
        }) {
            Text("Gerar Atributos")
        }

        // Mostrar os atributos gerados
        if (atributos.isNotEmpty()) {
            Spacer(Modifier.height(16.dp))
            Text("Atributos ($estilo):")
            atributos.forEach { (atributo, valor) ->
                Text("${atributo.name}: $valor")
            }
        }

        Spacer(Modifier.height(24.dp))

        // Botões
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(onClick = onBack) {
                Text("Voltar")
            }
            Button(onClick = {
                if (atributos.isNotEmpty()) {
                    val personagem = controller.criarPersonagem(
                        nome = nome,
                        estiloAtributo = estilo.lowercase(),
                        raca = raca,
                        classe = classe,
                        atributosManuais = atributos
                    )
                    onCreated(personagem)
                }
            }) {
                Text("Confirmar")
            }
        }
    }
}

@Composable
fun DropdownMenuBox(
    options: List<String>,
    selected: String,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text(selected)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}