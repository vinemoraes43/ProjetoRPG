package com.example.rpgfront.controller

import com.example.rpgfront.model.atributos.*
import com.example.rpgfront.model.classe.*
import com.example.rpgfront.model.raca.*
import com.example.rpgfront.model.personagem.*
import com.example.rpgfront.service.*

class PersonagemControllerAndroid {
    private val personagemService = PersonagemService()

    fun getRacas() : List<String> = listOf("Humano", "Elfo", "Anão")
    fun getClasses(): List<String> = listOf("Guerreiro", "Mago", "Ladino")

    fun gerarAtributos(estilo: String): Map<Atributo, Int> =
        GeradorAtributosFactory.criar(estilo).gerar()

    fun rolarValores(estilo: String): List<Int> = when (estilo.lowercase()) {

        "heroico", "heróico" ->
            (GeradorAtributosFactory.criar("heroico") as? HeroicoGerador)?.rolarValores() ?: emptyList()

        "aventureiro" ->
            (GeradorAtributosFactory.criar("aventureiro") as? HeroicoGerador)?.rolarValores() ?: emptyList()

        else -> emptyList()
    }

    fun criarPersonagem(
        nome: String,
        estiloAtributo: String,
        raca: String,
        classe: String,
        atributosManuais: Map<Atributo, Int>? = null

    ) : Personagem {

        // Tratamento de variações em raça
        val raca = RacaFactory.criar(
            when (raca.lowercase()) {
                "humano" -> "humano"
                "elfo" -> "elfo"
                "anão", "anao" -> "anao"
                else -> throw IllegalArgumentException("Raça inválida: $raca")
            }
        )

        // Tratamento de variações em classe
        val classe = ClasseFactory.criar(
            when (classe.lowercase()) {
                "guerreiro" -> "guerreiro"
                "mago" -> "mago"
                "ladino" -> "ladino"
                else -> throw IllegalArgumentException("Classe inválida: $classe")
            }
        )

        val atributosBase = atributosManuais ?: GeradorAtributosFactory
            .criar(estiloAtributo)
            .gerar()

        // Aplica bônus
        val personagemComBonus = personagemService.aplicarBonusRaca(
            PersonagemBuilder()
                .nome(nome.ifBlank { "SemNome" })
                .atributos(estiloAtributo)
                .raca(raca)
                .classe(classe)
                .build()
                .copy(atributos = atributosBase) // primeiro sem bônus
        )

        // retorna personagem com os dois valores
        return personagemComBonus.copy(atributosBase = atributosBase)
    }
}