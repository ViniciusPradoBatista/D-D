package org.example
import org.example.Raca
import java.util.Scanner


fun main() {
    val scanner = Scanner(System.`in`)
    val racas = listOf(
        Raca("Humano", mapOf("Forca" to 1, "Destreza" to 1, "Constituicao" to 1, "Inteligencia" to 1, "Sabedoria" to 1, "Carisma" to 1)),
        Raca("Dragão", mapOf("Forca" to 2, "Constituicao" to 2)),
        Raca("Esqueleto", mapOf("Destreza" to 2, "Constituicao" to 1)),
        Raca("Dracomante", mapOf("Inteligencia" to 2, "Carisma" to 1)),
        Raca("Zumbi", mapOf("Forca" to 2, "Constituicao" to 2))
    )

    val classes = listOf(
        Classe("Arqueiro", mapOf("Destreza" to 3)),
        Classe("Mago", mapOf("Inteligencia" to 3)),
        Classe("Guerreiro", mapOf("Forca" to 3))
    )

    val custosDeHabilidade = mapOf(
        8 to 0,
        9 to 1,
        10 to 2,
        11 to 3,
        12 to 4,
        13 to 5,
        14 to 7,
        15 to 9
    )

    fun distribuirPontos(personagem: Personagem, atributo: String, valorAtual: Int, pontosRestantes: Int): Int {
        var pontosDisponiveis = pontosRestantes
        while (true) {
            println("Escolha o valor desejado para $atributo (valor atual: $valorAtual): ")
            val valorEscolhido = scanner.nextInt()

            if (valorEscolhido in 8..15) {
                val custo = custosDeHabilidade[valorEscolhido] ?: 0
                if (valorEscolhido == valorAtual) {
                    println("Você manteve o valor de $atributo em $valorAtual. Nenhum ponto foi subtraído.")
                    return valorAtual
                } else if (custo <= pontosDisponiveis) {
                    pontosDisponiveis -= custo
                    println("Atributo $atributo definido para $valorEscolhido. Pontos restantes: $pontosDisponiveis")
                    return valorEscolhido
                } else {
                    println("Pontos insuficientes. Você tem $pontosDisponiveis pontos restantes.")
                }
            } else {
                println("Valor inválido. Escolha um valor entre 8 e 15.")
            }
        }
    }

    while (true) {
        // Escolha da raça
        println("Escolha sua raça:")
        racas.forEachIndexed { index, raca -> println("${index + 1} - ${raca.nome}") }
        val racaEscolha = scanner.nextInt()
        val racaSelecionada = racas[racaEscolha - 1]

        // Escolha da classe
        println("Escolha a classe da sua raça:")
        classes.forEachIndexed { index, classe -> println("${index + 1} - ${classe.nome}") }
        val classeEscolha = scanner.nextInt()
        val classeSelecionada = classes[classeEscolha - 1]

        // Criar personagem com a raça e classe selecionadas
        val personagem = Personagem()
        personagem.raca = racaSelecionada
        personagem.classe = classeSelecionada
        personagem.aplicarBonusRaca()
        personagem.aplicarBonusClasse()

        // Exibir atributos iniciais
        personagem.exibirAtributos()

        // Perguntar se deseja manter ou alterar
        println("Deseja manter ou alterar seu personagem?")
        println("1 - Manter")
        println("2 - Alterar")
        val escolhaFinal = scanner.nextInt()

        if (escolhaFinal == 1) {
            // Distribuição de pontos de habilidade
            var pontosRestantes = 27

            personagem.forca = distribuirPontos(personagem, "Força", personagem.forca, pontosRestantes)
            pontosRestantes -= custosDeHabilidade[personagem.forca] ?: 0

            personagem.destreza = distribuirPontos(personagem, "Destreza", personagem.destreza, pontosRestantes)
            pontosRestantes -= custosDeHabilidade[personagem.destreza] ?: 0

            personagem.constituicao = distribuirPontos(personagem, "Constituicao", personagem.constituicao, pontosRestantes)
            pontosRestantes -= custosDeHabilidade[personagem.constituicao] ?: 0

            personagem.inteligencia = distribuirPontos(personagem, "Inteligencia", personagem.inteligencia, pontosRestantes)
            pontosRestantes -= custosDeHabilidade[personagem.inteligencia] ?: 0

            personagem.sabedoria = distribuirPontos(personagem, "Sabedoria", personagem.sabedoria, pontosRestantes)
            pontosRestantes -= custosDeHabilidade[personagem.sabedoria] ?: 0

            personagem.carisma = distribuirPontos(personagem, "Carisma", personagem.carisma, pontosRestantes)
            pontosRestantes -= custosDeHabilidade[personagem.carisma] ?: 0

            personagem.calcularPontosDeVida()

            // Pedir nome do personagem
            println("Dê um nome ao seu personagem:")
            personagem.nome = scanner.next()

            // Exibir o personagem final
            println("Personagem criado:")
            println("Nome: ${personagem.nome}")
            println("Raça: ${personagem.raca?.nome}")
            println("Classe: ${personagem.classe?.nome}")
            println("Força: ${personagem.forca}")
            println("Destreza: ${personagem.destreza}")
            println("Constituição: ${personagem.constituicao}")
            println("Inteligência: ${personagem.inteligencia}")
            println("Sabedoria: ${personagem.sabedoria}")
            println("Carisma: ${personagem.carisma}")
            println("Pontos de Vida: ${personagem.pontosDeVida}")
            println("Pontos restantes: $pontosRestantes")
            break
        } else {
            // Reiniciar loop para alterar as escolhas
            println("Vamos começar novamente!")
        }
    }
}