package br.com.rpg.Controller

import br.com.rpg.Model.classe.*
import br.com.rpg.Model.raca.*
import br.com.rpg.Model.personagem.*
import br.com.rpg.Service.PersonagemService
import br.com.rpg.Service.SaveService

class PersonagemController (
    private val personagemService: PersonagemService = PersonagemService(),
    private val saveService: SaveService = SaveService()
) {
    fun criarPesonagem(
        nome: String,
        estiloAtributo: String,
        racaEscolhida: String,
        classeEscolhida: String
    ): Personagem {

        val raca = when (racaEscolhida.lowercase()) {
            "humano" -> Humano()
            "elfo" -> Elfo()
            "anao" -> Anao()
            else -> throw IllegalArgumentException("Raça não existente")
        }

        val classe = when (classeEscolhida.lowercase()) {
            "guerreiro" -> Guerreiro()
            "mago" -> Mago()
            "ladino" -> Ladino()
            else -> throw IllegalArgumentException("Classe não existente")
        }

        var personagem = PersonagemBuilder()
            .nome(nome)
            .atributos(estiloAtributo)
            .raca(raca)
            .classe(classe)
            .build()

        personagem = personagemService.aplicarBonusRaca(personagem)

        return personagem
    }
}