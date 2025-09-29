package com.example.rpgfront.model.raca

object RacaFactory {
    fun criar(nome: String): Raca = when (nome.lowercase()) {
        "humano" -> Humano()
        "elfo" -> Elfo()
        "anão", "anao" -> Anao()
         else -> throw IllegalArgumentException("Raça inválida: $nome")
    }
}