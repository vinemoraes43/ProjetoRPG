package com.example.rpgfront.data.local.dao

import androidx.room.*
import com.example.rpgfront.data.local.entity.PersonagemEntity

@Dao
interface PersonagemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(personagem: PersonagemEntity)

    @Query("SELECT * FROM personagens")
    suspend fun listarTodos(): List<PersonagemEntity>

    @Delete
    suspend fun deletar(personagem: PersonagemEntity)
}