package br.com.rpg.Model.classe
import kotlinx.serialization.Serializable

@Serializable
class Guerreiro : Classe {
    override val nome = "Guerreiro"
    override val habilidades = listOf(
        "Aparar (Nvl 1): sacrifica arma/escudo para anular dano de ataque físico.",
        "Maestria em Arma:" + "\n" +
                "\n" +
                "Nvl 1: +1 dano em 1 arma escolhida.\n" +
                "\n" +
                "Nvl 3: +2 dano em 2 armas.\n" +
                "\n" +
                "Nvl 10: +3 dano em todas as armas do mesmo grupo.",
        "Ataque Extra (Nvl 6): pode atacar 2 vezes com a mesma arma em que tenha maestria"
    )
}