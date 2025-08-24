package br.com.rpg.Model.raca
import kotlinx.serialization.Serializable

@Serializable
interface Raca {
    val nome: String
    val movimento: Int
    val infravisao: Int
    val alinhamento: String
    val habilidades: List<String>
}