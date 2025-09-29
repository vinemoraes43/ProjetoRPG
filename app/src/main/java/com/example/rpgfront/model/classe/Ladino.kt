package com.example.rpgfront.model.classe
import kotlinx.serialization.Serializable

@Serializable
class Ladino : Classe{
    override val nome = "Ladino"
    override val habilidades = listOf(
        "Ataque Furtivo: quando ataca de surpresa, o Ladino causa dano multiplicado " +
                "(×2 no início, aumentando para ×3 no 6º nível e ×4 no 10º).",
        "Ouvir Ruídos: consegue detectar sons atrás de portas ou em masmorras silenciosas, " +
                "com chance que aumenta conforme o nível.",
        "Talentos de Ladrão: habilidades especiais que o Ladino distribui pontos para melhorar " +
                "(como abrir fechaduras, desarmar armadilhas, etc.), " +
                "evoluindo conforme ganha níveis e recebendo bônus extras com alta Destreza. ",
        "Bônus de Destreza: recebe pontos extras conforme modificador de DES. " +
                "(Máx. 5 em cada talento, não pode evoluir +2 no mesmo nível)."
    )
}