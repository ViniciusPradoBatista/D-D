package org.example

import java.util.Scanner
import java.util.logging.Logger
import java.util.logging.Level
import java.util.logging.ConsoleHandler
import java.util.logging.SimpleFormatter

class Personagem {
    var nome: String = ""
    var raca: Raca? = null
    var classe: Classe? = null
    var forca: Int = 8
    var destreza: Int = 8
    var constituicao: Int = 8
    var inteligencia: Int = 8
    var sabedoria: Int = 8
    var carisma: Int = 8
    var pontosDeVida: Int = 10

    fun aplicarBonusRaca() {
        raca?.aplicarBonus(this)
    }

    fun aplicarBonusClasse() {
        classe?.bonusAtributos?.forEach { (atributo, bonus) ->
            when (atributo) {
                "Forca" -> forca += bonus
                "Destreza" -> destreza += bonus
                "Constituicao" -> constituicao += bonus
                "Inteligencia" -> inteligencia += bonus
                "Sabedoria" -> sabedoria += bonus
                "Carisma" -> carisma += bonus
            }
        }
    }

    fun calcularPontosDeVida() {
        pontosDeVida = 10 + ((constituicao - 10) / 2)
    }

    fun exibirAtributos() {
        println("Atributos Iniciais:")
        println("Força: $forca")
        println("Destreza: $destreza")
        println("Constituição: $constituicao")
        println("Inteligência: $inteligencia")
        println("Sabedoria: $sabedoria")
        println("Carisma: $carisma")
    }
}