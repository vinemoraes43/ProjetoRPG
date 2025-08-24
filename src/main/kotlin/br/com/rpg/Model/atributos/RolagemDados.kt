package br.com.rpg.Model.atributos

import kotlin.random.Random

object RolagemDados {
    // Rola X dados de Y faces → retorna a lista de resultados
    fun rolar(qtd : Int, faces: Int): List<Int>
    {
        return List(qtd) { Random.nextInt(1,faces + 1) }
    }

    // Estilo Clássico: 3d6 para cada atributo
    fun valoresClassico(): List<Int> =
        List(6) { rolar(3, 6).sum() }

    // Estilo Heróico: 4d6 descartando o menor, para cada atributo
    fun valoresHeroico(): List<Int> =
        List(6) { rolar(4, 6).sortedDescending().take(3).sum() }

    // Estilo Aventureiro: 3d6 para 6 valores
    fun valoresAventureiro(): List<Int> =
        List(6) { rolar(3, 6).sum() }
}