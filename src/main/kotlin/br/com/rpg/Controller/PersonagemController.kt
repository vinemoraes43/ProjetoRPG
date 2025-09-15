package br.com.rpg.Controller

import br.com.rpg.Model.atributos.Atributo
import br.com.rpg.Model.atributos.GeradorAtributosFactory
import br.com.rpg.Model.classe.*
import br.com.rpg.Model.raca.*
import br.com.rpg.Model.personagem.*
import br.com.rpg.Service.PersonagemService
import br.com.rpg.Service.SaveService

class PersonagemController (
    private val personagemService: PersonagemService = PersonagemService(),
    private val saveService: SaveService = SaveService()
) {
    fun gerarAtributos(estilo: String): Map<Atributo, Int> {
        return GeradorAtributosFactory.criar(estilo).gerar()
    }

    fun rolarValores(estilo: String): List<Int> {
        return when (estilo.lowercase()) {
            "heroico" -> (GeradorAtributosFactory.criar("heroico") as? br.com.rpg.Model.atributos.HeroicoGerador)
                ?.rolarValores() ?: emptyList()
            "aventureiro" -> (GeradorAtributosFactory.criar("aventureiro") as? br.com.rpg.Model.atributos.AventureiroGerador)
                ?.rolarValores() ?: emptyList()
            else -> emptyList()
        }
    }

    fun criarPesonagem(
        nome: String,
        estiloAtributo: String,
        racaEscolhida: String,
        classeEscolhida: String,
        atributosManuais: Map<Atributo, Int>? = null
    ): Personagem {

        val raca = RacaFactory.criar(racaEscolhida)
        val classe = ClasseFactory.criar(classeEscolhida)

        val atributos = atributosManuais ?: GeradorAtributosFactory
            .criar(estiloAtributo)
            .gerar()

        var personagem = PersonagemBuilder()
            .nome(nome)
            .atributos(estiloAtributo)
            .raca(raca)
            .classe(classe)
            .build()

        personagemService.validarAtributos(personagem.atributos)
        personagem = personagemService.aplicarBonusRaca(personagem)

        return personagem
    }
}