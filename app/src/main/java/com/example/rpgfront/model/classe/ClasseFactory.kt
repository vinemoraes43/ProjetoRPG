package com.example.rpgfront.model.classe

object ClasseFactory {
    fun criar(nome: String): Classe = when (nome.lowercase()) {
        "guerreiro" -> Guerreiro()
        "mago" -> Mago()
        "ladino" -> Ladino()
        else -> throw IllegalArgumentException("Classe inválida: $nome")
    }
}