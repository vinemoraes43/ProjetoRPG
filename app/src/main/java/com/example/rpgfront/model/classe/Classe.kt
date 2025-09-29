package com.example.rpgfront.model.classe

import kotlinx.serialization.Serializable

@Serializable
interface Classe {
    val nome: String
    val habilidades: List<String>
}