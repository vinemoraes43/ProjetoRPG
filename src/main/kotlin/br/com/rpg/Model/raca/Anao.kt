package br.com.rpg.Model.raca
import kotlinx.serialization.Serializable

@Serializable
class Anao : Raca {
    override val nome = "Elfo"
    override val movimento = 9
    override val infravisao = 18
    override val alinhamento = "tendem à neutralidade"
    override val habilidades = listOf(
        "Mineradores: Detectam anomalias em pedra (1 em 1d6 sem procurar, 1-2 em 1d6 procurando).",
        "Vigorosos: +1 em testes de JPC.",
        "Armas Grandes: Usam armas médias/pequenas; armas grandes anãs contam como médias para eles.",
        "Inimigos: Ataques contra orcs, ogros e hobgoblins são mais fáceis."
    )
}