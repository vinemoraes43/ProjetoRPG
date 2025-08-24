package br.com.rpg.Model.personagem

import br.com.rpg.Model.atributos.Atributo
import br.com.rpg.Model.atributos.GeradorAtributosFactory
import br.com.rpg.Model.raca.Raca
import br.com.rpg.Model.classe.Classe
import kotlinx.serialization.Serializable

@Serializable
class PersonagemBuilder {
    private var nome: String = "Sem nome"
    private var atributos: Map<Atributo, Int> = emptyMap()
    private var raca : Raca? = null
    private var classe : Classe? = null

    fun nome(nome: String) = apply { this.nome = nome }
    fun atributos(estilo: String) = apply { this.atributos = GeradorAtributosFactory.criar(estilo).gerar() }
    fun raca(raca: Raca) = apply { this.raca = raca }
    fun classe(classe: Classe) = apply { this.classe = classe }

    fun build(): Personagem {
        return Personagem(
            nome = nome,
            atributos = atributos,
            raca = raca ?: throw IllegalArgumentException("Raça não definida"),
            classe = classe ?: throw IllegalStateException("Classe não definida")
        )
    }
}