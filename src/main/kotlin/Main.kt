package org.example
import org.example.Raca
import java.util.Scanner


fun main() {
    val scanner = Scanner(System.`in`)

    val racas = listOf(
        Raca("Anão", "Anão da montanha", mapOf("Forca" to 2, "Constituicao" to 2)),
        Raca("Anão", "Anão da colina", mapOf("Constituicao" to 2, "Sabedoria" to 1)),
        Raca("Elfo", "Alto elfo", mapOf("Destreza" to 2, "Inteligencia" to 1)),
        Raca("Elfo", "Elfo da floresta", mapOf("Destreza" to 2, "Sabedoria" to 1)),
        Raca("Halfling", "Halfling pés-leves", mapOf("Destreza" to 2, "Carisma" to 1)),
        Raca("Halfling", "Halfling robusto", mapOf("Destreza" to 2, "Constituicao" to 1)),
        Raca("Humano", null, mapOf("Forca" to 1, "Destreza" to 1, "Constituicao" to 1, "Inteligencia" to 1, "Sabedoria" to 1, "Carisma" to 1)),
        Raca("Draconato", null, mapOf("Forca" to 2, "Carisma" to 1)),
        Raca("Gnomo", "Gnomo das rochas", mapOf("Inteligencia" to 2, "Constituicao" to 1)),
        Raca("Gnomo", "Gnomo da floresta", mapOf("Inteligencia" to 2, "Destreza" to 1)),
        Raca("Meio-elfo", null, mapOf("Carisma" to 2, "Forca" to 1, "Destreza" to 1)),
        Raca("Meio-orc", null, mapOf("Forca" to 2, "Constituicao" to 1)),
        Raca("Tiefling", null, mapOf("Inteligencia" to 1, "Carisma" to 2))
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

    fun distribuirPontos(personagem: Personagem, atributo: String, valorAtual: Int, pontosRestantes: Int): Pair<Int, Int> {
        var pontosDisponiveis = pontosRestantes
        while (true) {
            println("Escolha o valor desejado para $atributo (valor atual: $valorAtual): ")
            val valorEscolhido = scanner.nextInt()

            if (valorEscolhido in 8..15) {
                val custo = custosDeHabilidade[valorEscolhido] ?: 0
                if (valorEscolhido == valorAtual) {
                    println("Você manteve o valor de $atributo em $valorAtual. Nenhum ponto foi subtraído.")
                    return Pair(valorAtual, pontosDisponiveis)
                } else if (custo <= pontosDisponiveis) {
                    pontosDisponiveis -= custo
                    println("Atributo $atributo definido para $valorEscolhido. Pontos restantes: $pontosDisponiveis")
                    return Pair(valorEscolhido, pontosDisponiveis)
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
        racas.forEachIndexed { index, raca -> println("${index + 1} - ${raca.nome} ${raca.subRaca?.let { "($it)" } ?: ""}") }
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
            var repetirDistribuicao = true

            while (repetirDistribuicao) {
                repetirDistribuicao = false

                val (forca, pontosAposForca) = distribuirPontos(personagem, "Força", personagem.forca, pontosRestantes)
                personagem.forca = forca
                pontosRestantes = pontosAposForca

                val (destreza, pontosAposDestreza) = distribuirPontos(personagem, "Destreza", personagem.destreza, pontosRestantes)
                personagem.destreza = destreza
                pontosRestantes = pontosAposDestreza

                val (constituicao, pontosAposConstituicao) = distribuirPontos(personagem, "Constituicao", personagem.constituicao, pontosRestantes)
                personagem.constituicao = constituicao
                pontosRestantes = pontosAposConstituicao

                val (inteligencia, pontosAposInteligencia) = distribuirPontos(personagem, "Inteligencia", personagem.inteligencia, pontosRestantes)
                personagem.inteligencia = inteligencia
                pontosRestantes = pontosAposInteligencia

                val (sabedoria, pontosAposSabedoria) = distribuirPontos(personagem, "Sabedoria", personagem.sabedoria, pontosRestantes)
                personagem.sabedoria = sabedoria
                pontosRestantes = pontosAposSabedoria

                val (carisma, pontosAposCarisma) = distribuirPontos(personagem, "Carisma", personagem.carisma, pontosRestantes)
                personagem.carisma = carisma
                pontosRestantes = pontosAposCarisma

                // Verificação para garantir que os pontos sejam exatamente zero no final
                if (pontosRestantes != 0) {
                    println("Erro: Você deve usar exatamente 27 pontos. Por favor, ajuste os valores dos atributos.")
                    repetirDistribuicao = true
                }
            }

            personagem.calcularPontosDeVida()

            // Pedir nome do personagem
            println("Dê um nome ao seu personagem:")
            personagem.nome = scanner.next()

            // Exibir o personagem final
            println("Personagem criado:")
            println("Nome: ${personagem.nome}")
            println("Raça: ${personagem.raca?.nome} ${personagem.raca?.subRaca?.let { "($it)" } ?: ""}")
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