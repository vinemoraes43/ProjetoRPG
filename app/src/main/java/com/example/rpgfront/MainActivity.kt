package com.example.rpgfront

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.rpgfront.controller.PersonagemControllerAndroid
import com.example.rpgfront.model.personagem.Personagem
import com.example.rpgfront.view.ui.MainMenuScreen
import com.example.rpgfront.view.ui.CreateCharacterScreen
import com.example.rpgfront.view.ui.CharacterPreviewScreen

class MainActivity : ComponentActivity() {
    private val controller = PersonagemControllerAndroid()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val screen = remember { mutableStateOf<Screen>(Screen.Main) }

            when (val s = screen.value) {
                is Screen.Main -> MainMenuScreen(
                    onCreateCharacter = { screen.value = Screen.Create },
                    onExit = { finish() }
                )

                is Screen.Create -> CreateCharacterScreen(
                    controller = controller,
                    onBack = { screen.value = Screen.Main },
                    onCreated = { personagem ->
                        screen.value = Screen.Preview(personagem)
                    }
                )

                is Screen.Preview -> CharacterPreviewScreen(
                    personagem = s.personagem,
                    onBack = { screen.value = Screen.Main },
                    onConfirm = { screen.value = Screen.Main }
                )
            }
        }
    }

    sealed interface Screen {
        object Main : Screen
        object Create : Screen
        data class Preview(val personagem: Personagem) : Screen
    }
}