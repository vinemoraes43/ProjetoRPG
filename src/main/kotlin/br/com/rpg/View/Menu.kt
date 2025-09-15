package br.com.rpg.View

import br.com.rpg.Controller.PersonagemController
import br.com.rpg.Model.atributos.*

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
                "2" -> println("Save de personagens não está disponível.")
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
            else -> throw IllegalArgumentException("Raça não existente")
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
            else -> throw IllegalArgumentException("Estilo de atributo não existente")
        }

        val atributosEscolhidos: Map<Atributo, Int> = when (estilo) {
            "classico" -> {
                controller.gerarAtributos(estilo)
            }
            "heroico", "aventureiro" -> {
                val valores = controller.rolarValores(estilo).toMutableList()
                distribuir(valores)
            }
            else -> controller.gerarAtributos("classico")
        }

        val personagem = controller.criarPesonagem(
            nome = nome,
            estiloAtributo = estilo,
            racaEscolhida = raca,
            classeEscolhida = classe,
            atributosManuais = atributosEscolhidos
        )

        println("\n=== Personagem Criado com Sucesso ===")
        println("Nome: ${personagem.nome}")
        println("Nível: ${personagem.nivel}")
        println("Raça: ${personagem.raca.nome}")
        println("Classe: ${personagem.classe.nome}")
        println("Atributos: ${personagem.atributos}")
        println("O salvamento ainda não está por completo.")
    }

    private fun distribuir(valores: MutableList<Int>): Map<Atributo, Int> {
        val atributos = mutableMapOf<Atributo, Int>()

        for (atributo in Atributo.values()) {
            println("\nAtribuindo valor para ${atributo.name}")
            println("Valores disponíveis: $valores")
            print("Escolha um valor: ")

            val escolhido = readln().toIntOrNull()
            if (escolhido != null && valores.contains(escolhido)) {
                atributos[atributo] = escolhido
                valores.remove(escolhido)
            } else {
                println("Valor inválido, tente novamente!")
                return distribuir(valores) // reinicia para não travar o jogo
            }
        }

        return atributos
    }
}