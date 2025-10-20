package com.example.rpgfront.data.repository

import android.content.Context
import com.example.rpgfront.data.local.AppDatabase
import com.example.rpgfront.data.local.entity.PersonagemEntity
import com.example.rpgfront.model.atributos.Atributo
import com.example.rpgfront.model.classe.ClasseFactory
import com.google.gson.Gson
import com.example.rpgfront.model.personagem.Personagem
import com.example.rpgfront.model.raca.RacaFactory

class PersonagemRepository(context: Context) {

    private val dao = AppDatabase.getDatabase(context).personagemDao()
    private val gson = Gson()

    suspend fun salvar(personagem: Personagem) {
        val jsonAtributos = gson.toJson(personagem.atributos)
        val entity = PersonagemEntity(
            nome = personagem.nome,
            raca = personagem.raca.nome,
            classe = personagem.classe.nome,
            estiloAtributo = personagem.estiloAtributo,
            atributosJson = jsonAtributos
        )
        dao.inserir(entity)
    }

    suspend fun listarTodosPersonagens(): List<Personagem> {
        val listaEntity = dao.listarTodos()

        return listaEntity.map { entity ->
            val atributos: Map<Atributo, Int> =
                gson.fromJson(entity.atributosJson, Map::class.java)
                    .mapKeys { Atributo.valueOf(it.key.toString()) }
                    .mapValues { (it.value as Double).toInt() }

            Personagem(
                nome = entity.nome,
                raca = RacaFactory.criar(entity.raca),
                classe = ClasseFactory.criar(entity.classe),
                atributos = atributos
            )
        }
    }

    suspend fun deletar(personagem: PersonagemEntity) = dao.deletar(personagem)
}