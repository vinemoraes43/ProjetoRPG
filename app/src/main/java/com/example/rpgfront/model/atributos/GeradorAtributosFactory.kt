package com.example.rpgfront.model.atributos

interface GeradorAtributos {
    fun gerar(): Map<Atributo, Int>
    fun rolarValores(): List<Int> = emptyList()
}

class ClassicoGerador : GeradorAtributos {
    override fun gerar(): Map<Atributo, Int> =
        Atributo.values().zip(RolagemDados.valoresClassico()).toMap()
}

class HeroicoGerador : GeradorAtributos {
    override fun gerar(): Map<Atributo, Int> {
        val valores = RolagemDados.valoresHeroico()
        return Atributo.values().zip(valores).toMap()
    }
}

class AventureiroGerador : GeradorAtributos {
    override fun gerar(): Map<Atributo, Int> {
        val valores = RolagemDados.valoresHeroico()
        return Atributo.values().zip(valores).toMap()
    }
}

object GeradorAtributosFactory {
    fun criar(estilo : String) : GeradorAtributos = when (estilo.lowercase()) {
        "classico", "clássico" -> ClassicoGerador()
        "heroico", "heróico" -> HeroicoGerador()
        "aventureiro" -> AventureiroGerador()
        else -> throw IllegalArgumentException("Estilo inválido: $estilo")
    }
}