package com.example.rpgfront.model.atributos

interface ModificadorStrategy {
    fun calcular(valor : Int) : Int
}