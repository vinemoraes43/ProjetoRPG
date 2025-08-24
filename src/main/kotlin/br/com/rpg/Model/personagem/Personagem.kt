package br.com.rpg.Model.personagem

import br.com.rpg.Model.atributos.Atributo
import br.com.rpg.Model.raca.Raca
import br.com.rpg.Model.classe.Classe
import kotlinx.serialization.Serializable

@Serializable
data class Personagem(
    val nome: String,
    val nivel: Int = 1,
    val atributos: Map<Atributo, Int>,
    val raca : Raca,
    val classe : Classe
)
