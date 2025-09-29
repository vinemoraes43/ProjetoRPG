package com.example.rpgfront.model.raca
import kotlinx.serialization.Serializable

@Serializable
class Elfo : Raca{
    override val nome = "Elfo"
    override val movimento = 9
    override val infravisao = 18
    override val alinhamento = "tendem à neutralidade"
    override val habilidades = listOf(
        "Percepção Natural: Chance de detectar portas secretas " +
                "(1 em 1d6 sem procurar, 1-2 em 1d6 procurando).",
        "Graciosos: +1 em testes de JPD",
        "Arma Racial: +1 no dano com arcos",
        "Imunidades: Imunes a sono mágico e paralisia de Ghoul"
    )
    override fun bonusDescricao() = "+2 em Destreza"
}