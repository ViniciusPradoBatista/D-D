package org.example

import java.util.logging.Logger
import java.util.logging.Level
import java.util.logging.ConsoleHandler
import java.util.logging.SimpleFormatter
import java.util.Scanner

class DistribuicaoPersonalizada {
    private val logger: Logger = Logger.getLogger(DistribuicaoPersonalizada::class.java.name)
    private val scanner = Scanner(System.`in`)
    private var pontosRestantes = 27

    init {
        val consoleHandler = ConsoleHandler()
        consoleHandler.level = Level.ALL
        consoleHandler.formatter = SimpleFormatter()
        logger.addHandler(consoleHandler)
        logger.useParentHandlers = false
    }

    fun distribuirAtributos(personagem: Personagem) {
        logger.info("Você tem $pontosRestantes pontos para distribuir entre os atributos.")
        personagem.forca = definirAtributo("Força", personagem.forca)
        personagem.destreza = definirAtributo("Destreza", personagem.destreza)
        personagem.constituicao = definirAtributo("Constituição", personagem.constituicao)
        personagem.inteligencia = definirAtributo("Inteligência", personagem.inteligencia)
        personagem.sabedoria = definirAtributo("Sabedoria", personagem.sabedoria)
        personagem.carisma = definirAtributo("Carisma", personagem.carisma)
    }

    private fun definirAtributo(nomeAtributo: String, valorInicial: Int): Int {
        var novoValor = valorInicial
        var pontosAdicionados: Int

        while (true) {
            println("Preencha a $nomeAtributo (valor inicial: $valorInicial): ")
            pontosAdicionados = scanner.nextInt()
            val valorFinal = valorInicial + pontosAdicionados

            if (pontosAdicionados <= pontosRestantes && valorFinal >= 8) {
                novoValor = valorFinal
                pontosRestantes -= pontosAdicionados

                println("$novoValor pontos de $nomeAtributo")
                println("$pontosRestantes pontos restantes")
                println("Deseja manter os pontos ou alterar?")
                println("1 - Manter")
                println("2 - Alterar")

                val escolha = scanner.nextInt()
                if (escolha == 1) {
                    break
                } else {
                    // Resetando os valores
                    pontosRestantes += pontosAdicionados
                    novoValor = valorInicial
                    println("Os pontos de $nomeAtributo foram resetados para $valorInicial.")
                }
            } else {
                println("Pontos inválidos. Você tem $pontosRestantes pontos restantes e o valor final de $nomeAtributo deve ser pelo menos 8.")
            }
        }
        return novoValor
    }
}