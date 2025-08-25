package br.com.rpg.Model.atributos

interface GeradorAtributos {
    fun gerar(): Map<Atributo, Int>
}

class ClassicoGerador : GeradorAtributos {
    override fun gerar(): Map<Atributo, Int> =
        Atributo.values().zip(RolagemDados.valoresClassico()).toMap()
}

class HeroicoGerador : GeradorAtributos {
    override fun gerar(): Map<Atributo, Int> {
        val valores = RolagemDados.valoresHeroico().toMutableList()
        println("\nValores rolados (Heróico): $valores")
        return distribuir(valores)
    }
}

class AventureiroGerador : GeradorAtributos {
    override fun gerar(): Map<Atributo, Int> {
        val valores = RolagemDados.valoresAventureiro().toMutableList()
        println("\nValores rolados (Aventureiro): $valores")
        return distribuir(valores)
    }
}

private fun distribuir(valores: MutableList<Int>): Map<Atributo, Int> {
    val atributos = mutableMapOf<Atributo, Int>()
    for (atributo in Atributo.values()) {
        println("\nAtribuindo valor para ${atributo.name}")
        println("Valores disponíveis: $valores")
        print("Escolha um valor: ")
        val escolhido = readln().toIntOrNull()
        if (escolhido != null && valores.contains(escolhido)) {
            atributos[atributo] = escolhido
            valores.remove(escolhido)
        } else {
            println("Valor inválido, tente novamente!")
            return distribuir(valores) // reinicia para não travar o jogo
        }
    }
    return atributos
}

object GeradorAtributosFactory {
    fun criar(estilo : String) : GeradorAtributos = when (estilo.lowercase()) {
        "classico" -> ClassicoGerador()
        "heroico" -> HeroicoGerador()
        "aventureiro" -> AventureiroGerador()
        else -> throw IllegalArgumentException("Estilo inválido")
    }
}