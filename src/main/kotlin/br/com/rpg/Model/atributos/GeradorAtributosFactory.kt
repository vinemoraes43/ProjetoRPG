package br.com.rpg.Model.atributos

interface GeradorAtributos {
    fun gerar(): Map<Atributo, Int>
}

class ClassicoGerador : GeradorAtributos {
    override fun gerar(): Map<Atributo, Int> =
        Atributo.values().zip(RolagemDados.valoresClassico()).toMap()
}

class HeroicoGerador : GeradorAtributos {
    fun rolarValores(): List<Int> = RolagemDados.valoresHeroico()
    override fun gerar(): Map<Atributo, Int> =
        Atributo.values().zip(rolarValores().sortedDescending()).toMap()
}

class AventureiroGerador : GeradorAtributos {
    fun rolarValores(): List<Int> = RolagemDados.valoresAventureiro()
    override fun gerar(): Map<Atributo, Int> =
        Atributo.values().zip(rolarValores().sortedDescending()).toMap()
}

object GeradorAtributosFactory {
    fun criar(estilo : String) : GeradorAtributos = when (estilo.lowercase()) {
        "classico" -> ClassicoGerador()
        "heroico" -> HeroicoGerador()
        "aventureiro" -> AventureiroGerador()
        else -> throw IllegalArgumentException("Estilo inválido")
    }
}