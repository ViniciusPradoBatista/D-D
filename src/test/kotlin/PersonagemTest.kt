package org.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PersonagemTest {

    @Test
    fun `CalcularPontos`() {
        val personagem = Personagem().apply {
            constituicao = 14
            calcularPontosDeVida()
        }
        println("Constituição: ${personagem.constituicao}, Pontos de Vida Calculados: ${personagem.pontosDeVida}")
        assertEquals(12, personagem.pontosDeVida)
    }

    @Test
    fun `BonusRaca`() {
        val raca = Raca("Anão", "Anão da montanha", mapOf("Forca" to 2, "Constituicao" to 2))
        val personagem = Personagem()
        raca.aplicarBonus(personagem)

        println("Bônus aplicados para a raça ${raca.nome}: Força = ${personagem.forca}, Constituição = ${personagem.constituicao}")
        assertEquals(10, personagem.forca)
        assertEquals(10, personagem.constituicao)
    }
}