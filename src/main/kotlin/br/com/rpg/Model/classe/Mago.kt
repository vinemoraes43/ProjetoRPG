package br.com.rpg.Model.classe
import kotlinx.serialization.Serializable

@Serializable
class Mago : Classe {
    override val nome = "Mago"
    override val habilidades = listOf(
        "Magias Arcanas (Nvl 1+): lança magias a partir do grimório, que deve ser estudado diariamente.\n" +
                "\n" +
                "Magias iniciais: começa com 3 magias de 1º círculo + 1 aleatória.\n" +
                "\n" +
                "Novas magias: pode copiar de grimórios e pergaminhos.\n" +
                "\n" +
                "Círculos superiores: pode escrever no grimório, mas só memoriza quando atingir o nível adequado.",
        "Ler Magias (Nvl 1+): 1 vez/dia por nível, decifra inscrições mágicas.",
        "Detectar Magias (Nvl 1+): 1 vez/dia por nível, percebe presença de magia até 9m + 3m / nível.\n" +
                "\n" +
        "Concentração: 1d8 rodadas → 1d4 (Nvl 6) → 1 rodada (Nvl 10)."
    )
}