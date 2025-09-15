package br.com.rpg.Service

import br.com.rpg.Model.atributos.Atributo
import br.com.rpg.Model.personagem.Personagem

class PersonagemService {
    fun aplicarBonusRaca(personagem: Personagem): Personagem {
        val novosAtributos = personagem.atributos.toMutableMap()

        when (personagem.raca.nome.lowercase()) {
            "humano" -> {
                // Humanos: +1 em todos os atributos
                novosAtributos.keys.forEach { novosAtributos[it] = (novosAtributos[it] ?: 0) + 1 }
            }
            "elfo" -> {
                // Elfos: +2 Destreza
                novosAtributos[Atributo.DESTREZA] =
                    (novosAtributos[Atributo.DESTREZA] ?: 0) + 2
            }
            "anao" -> {
                // Anões: +2 Constituição
                novosAtributos[Atributo.CONSTITUICAO] =
                    (novosAtributos[Atributo.CONSTITUICAO] ?: 0) + 2
            }
        }

        return personagem.copy(atributos = novosAtributos)
    }

    fun validarAtributos(atributos: Map<Atributo, Int>) {
        val faltando = Atributo.values().filterNot { atributos.containsKey(it) }
        if (faltando.isNotEmpty()) {
            throw IllegalArgumentException("Faltando atribuir valores para: $faltando")
        }
    }

}