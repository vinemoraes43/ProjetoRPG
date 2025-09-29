package com.example.rpgfront.model.personagem

import com.example.rpgfront.model.atributos.Atributo
import com.example.rpgfront.model.raca.Raca
import com.example.rpgfront.model.classe.Classe
import kotlinx.serialization.Serializable

@Serializable
data class Personagem(
    val nome: String,
    val nivel: Int = 1,
    val raca : Raca,
    val classe : Classe,
    val atributos: Map<Atributo, Int>,          // finais (com bônus)
    val atributosBase: Map<Atributo, Int> = atributos // base (sem bônus)
)
