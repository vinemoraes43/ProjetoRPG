package com.example.rpgfront.model.personagem

import com.example.rpgfront.model.atributos.*
import com.example.rpgfront.model.atributos.GeradorAtributosFactory
import com.example.rpgfront.model.raca.Raca
import com.example.rpgfront.model.classe.Classe
import kotlinx.serialization.Serializable

@Serializable
class PersonagemBuilder {
    private var nome: String = "Sem nome"
    private var nivel: Int = 1
    private var raca : Raca? = null
    private var classe : Classe? = null
    private var estiloAtributo: String = "classico"
    private var atributos: Map<Atributo, Int> = emptyMap()

    fun nome(nome: String) = apply { this.nome = nome }
    fun raca(raca: Raca) = apply { this.raca = raca }
    fun classe(classe: Classe) = apply { this.classe = classe }
    fun estilo(estilo: String) = apply { this.estiloAtributo = estilo }
    fun atributos(estilo: String) = apply { this.atributos = GeradorAtributosFactory.criar(estilo).gerar() }

    fun build(): Personagem {
        return Personagem(
            nome = nome,
            nivel = nivel,
            raca = raca ?: throw IllegalArgumentException("Raça não definida"),
            classe = classe ?: throw IllegalStateException("Classe não definida"),
            estiloAtributo = estiloAtributo,
            atributos = atributos,
        )
    }
}