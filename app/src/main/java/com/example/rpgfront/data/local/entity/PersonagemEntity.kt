package com.example.rpgfront.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rpgfront.model.personagem.Personagem

@Entity(tableName = "personagens")
data class PersonagemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val raca: String,
    val classe: String,
    val estiloAtributo: String,
    val atributosJson: String
)

fun PersonagemEntity.toModel(): Personagem {
    val gson = com.google.gson.Gson()
    val atributosMap: Map<com.example.rpgfront.model.atributos.Atributo, Int> =
        gson.fromJson(atributosJson, object : com.google.gson.reflect.TypeToken<Map<com.example.rpgfront.model.atributos.Atributo, Int>>() {}.type)

    return com.example.rpgfront.model.personagem.Personagem(
        nome = nome,
        raca = com.example.rpgfront.model.raca.RacaFactory.criar(raca),
        classe = com.example.rpgfront.model.classe.ClasseFactory.criar(classe),
        atributos = atributosMap,
        nivel = 1,
        estiloAtributo = estiloAtributo
    )
}
