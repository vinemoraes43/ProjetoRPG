package br.com.rpg.Model.atributos

interface ModificadorStrategy {
    fun calcular(valor : Int) : Int
}