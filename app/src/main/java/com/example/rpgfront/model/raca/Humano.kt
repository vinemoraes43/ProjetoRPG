package com.example.rpgfront.model.raca
import kotlinx.serialization.Serializable

@Serializable
class Humano : Raca {
    override val nome = "Humano"
    override val movimento = 9
    override val infravisao = 0
    override val alinhamento = "Qualquer"
    override val habilidades = listOf(
        "Aprendizado: +10% em toda experiência (XP)",
        "Adaptabilidade: +1 em uma Jogada de Proteção à escolha"
    )
    override fun bonusDescricao() = "+1 em todos os atributos"
}