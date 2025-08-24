package br.com.rpg.Model.classe

import kotlinx.serialization.Serializable

@Serializable
interface Classe {
    val nome: String
    val habilidades: List<String>
}