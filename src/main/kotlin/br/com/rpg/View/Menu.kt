package br.com.rpg.View

import br.com.rpg.Controller.PersonagemController

class Menu (
    private val controller: PersonagemController = PersonagemController()
) {
    fun iniciar() {
        while (true) {
            println("\n=== MENU PRINCIPAL ===")
            println("1. Iniciar Jogo (Criar Personagem)")
            println("2. Carregar Jogo (Em desenvolvimento)")
            println("3. Sair")
            print("Escolha uma opção: ")

            when (readln()) {
                "1" -> criarPersonagem()
                "2" -> println("Função de carregar ainda não está disponível.")
                "3" -> {
                    println("Saindo do jogo... Até mais!")
                    return
                }
                else -> println("Opção inválida, tente novamente!")
            }
        }
    }

    private fun criarPersonagem() {
        print("\nDigite o nome do personagem: ")
        val nome = readln()

        println("\nEscolha a raça:")
        println("1. Humano")
        println("2. Elfo")
        println("3. Anão")
        val raca = when (readln()) {
            "1" -> "humano"
            "2" -> "elfo"
            "3" -> "anao"
            else -> throw IllegalArgumentException("Classe não existente")
        }

        println("\nEscolha a classe:")
        println("1. Guerreiro")
        println("2. Mago")
        println("3. Ladino")
        val classe = when (readln()) {
            "1" -> "guerreiro"
            "2" -> "mago"
            "3" -> "ladino"
            else -> throw IllegalArgumentException("Classe não existente")
        }

        println("\nEscolha o estilo de atributos:")
        println("1. Clássico (3d6 por atributo)")
        println("2. Heróico (4d6 descarta o menor)")
        println("3. Aventureiro (3d6, distribui nos atributos)")
        val estilo = when (readln()) {
            "1" -> "classico"
            "2" -> "heroico"
            "3" -> "aventureiro"
            else -> "classico"
        }

        val personagem = controller.criarPesonagem(
            nome = nome,
            estiloAtributo = estilo,
            racaEscolhida = raca,
            classeEscolhida = classe
        )

        println("\n=== Personagem Criado com Sucesso ===")
        println("Nome: ${personagem.nome}")
        println("Nível: ${personagem.nivel}")
        println("Raça: ${personagem.raca.nome}")
        println("Classe: ${personagem.classe.nome}")
        println("Atributos: ${personagem.atributos}")
        println("O salvamento ainda não está por completo.")
    }
}